package com.example.sawit.fragments;

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.sawit.R

class FieldDetailFragment : Fragment(R.layout.fragment_fields_detail) {
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
