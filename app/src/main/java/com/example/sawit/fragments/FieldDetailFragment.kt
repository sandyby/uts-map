package com.example.sawit.fragments;

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sawit.R
import com.example.sawit.viewmodels.FieldViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FieldDetailFragment : Fragment(R.layout.fragment_fields_detail) {
    private val fieldViewModel: FieldViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ibEditData = view.findViewById<ImageView>(R.id.mb_edit_data_btn)
        val ibAddData = view.findViewById<ImageView>(R.id.mb_add_data_btn)

        ibEditData.setOnClickListener {
            val editFieldDataFragment = EditFieldDataFragment()

            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_fragments_fields_detail, editFieldDataFragment)
                .addToBackStack(null)
                .commit()
//            Toast.makeText(requireContext(), "Canceled adding new field", Toast.LENGTH_SHORT).show()
//            parentFragmentManager.popBackStack()
        }

        ibAddData.setOnClickListener {
            val addFieldDataFragment = AddFieldDataFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.cl_fragments_fields_detail, addFieldDataFragment)
                .addToBackStack(null)
                .commit()
            parentFragmentManager.popBackStack()
        }
    }
}
