package com.example.sawit.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.sawit.R
import com.example.sawit.databinding.ActivityCreateEditBinding
import com.example.sawit.viewmodels.FieldViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateEditBinding
    private val calendar = Calendar.getInstance()

    // 1. Get an instance of FieldViewModel
    private val fieldViewModel: FieldViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
        setupActivityTypeDropdown()
        observeFields() // 2. Start observing the fields data
    }

    private fun setupListeners() {
        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.editTextDate.setOnClickListener {
            showDatePicker()
        }

        binding.buttonSave.setOnClickListener {
            // TODO: Add validation and save logic here
            Toast.makeText(this, "New Activity Added!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupActivityTypeDropdown() {
        val activityTypes = resources.getStringArray(R.array.activity_types)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, activityTypes)
        binding.autoCompleteActivityType.setAdapter(adapter)
    }

    /**
     * Observes the fields data from FieldViewModel and populates the dropdown.
     */
    private fun observeFields() {
        // Use lifecycleScope to safely collect data from the StateFlow
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                fieldViewModel.fieldsData.collect { fields ->
                    // 3. When data is received, populate the dropdown
                    if (fields.isNotEmpty()) {
                        // Extract just the names from the list of Field objects
                        val fieldNames = fields.map { it.fieldName }
                        val adapter = ArrayAdapter(this@CreateEditActivity, android.R.layout.simple_dropdown_item_1line, fieldNames)
                        binding.autoCompleteField.setAdapter(adapter)
                    }
                }
            }
        }
    }


    private fun showDatePicker() {
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateDateInView()
        }

        DatePickerDialog(
            this,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.editTextDate.setText(sdf.format(calendar.time))
    }
}

