package com.example.sawitku.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.example.sawitku.R

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