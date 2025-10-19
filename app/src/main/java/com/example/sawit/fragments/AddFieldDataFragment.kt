package com.example.sawit.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sawit.R
import com.example.sawit.viewmodels.FieldViewModel

class AddFieldDataFragment : Fragment(R.layout.activity_add_data) {
    private val fieldViewModel: FieldViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivBack = view.findViewById<ImageView>(R.id.iv_back)
//        val etFieldName = view.findViewById<EditText>(R.id.et_field_name)
        val btnSave = view.findViewById<Button>(R.id.btn_add)

        ivBack.setOnClickListener {
            Toast.makeText(requireContext(), "Canceled adding new field", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

        btnSave.setOnClickListener {
            Toast.makeText(requireContext(), "Successfully created new field!", Toast.LENGTH_LONG).show()
            parentFragmentManager.popBackStack()
        }
    }
}

