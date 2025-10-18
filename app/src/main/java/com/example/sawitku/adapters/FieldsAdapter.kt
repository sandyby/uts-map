package com.example.sawitku.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FieldsAdapter() : RecyclerView.Adapter<FieldsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivFieldPhoto: ImageView
        val tvFieldName: TextView
        val tvFieldArea: TextView
        val tvFieldDesc: TextView
        val tvFieldLocation: TextView

        init {
            ivFieldPhoto = view.findViewById<>()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}