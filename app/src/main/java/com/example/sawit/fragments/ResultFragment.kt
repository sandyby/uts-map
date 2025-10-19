package com.example.sawit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sawit.R

class ResultFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val btnBack = view.findViewById<ImageView>(R.id.iv_btn_back)
        btnBack.setOnClickListener {
            try {
                findNavController().popBackStack()
            } catch (e: Exception) {
                requireActivity().supportFragmentManager.popBackStack()
            }
        }


        return view
    }
}
