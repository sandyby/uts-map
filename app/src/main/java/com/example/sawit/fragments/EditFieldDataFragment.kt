package com.example.sawit.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sawit.R

class EditFieldDataFragment : Fragment(R.layout.activity_edit_data) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ivBack = view.findViewById<ImageView>(R.id.iv_back)
        val btnSave = view.findViewById<Button>(R.id.btn_save)

        ivBack.setOnClickListener {
            Toast.makeText(requireContext(), "Canceled edit field", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
        }

        btnSave.setOnClickListener {
            Toast.makeText(requireContext(), "Successfully edited field!", Toast.LENGTH_LONG).show()
            parentFragmentManager.popBackStack()
        }
    }
}

