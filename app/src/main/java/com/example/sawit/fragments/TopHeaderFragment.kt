package com.example.sawit.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sawit.R

class TopHeaderFragment : Fragment(R.layout.fragment_top_header) {
    private lateinit var tvTitle: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvTitle = view.findViewById(R.id.tv_header_title)
    }

    fun setTopHeaderFragmentTitle(title: String) {
        if (this::tvTitle.isInitialized)
            tvTitle.text = title
    }
}