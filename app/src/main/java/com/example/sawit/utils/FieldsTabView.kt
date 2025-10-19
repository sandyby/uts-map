package com.example.sawit.utils

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.sawit.R

class FieldsTabView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private val tvDetails: TextView
    private val tvStatistics: TextView
    private val select: TextView
    var onTabSelectedListener: ((tabIndex: Int) -> Unit)? = null

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.fields_tab, this, true)

        tvDetails = findViewById(R.id.tv_tab_details)
        tvStatistics = findViewById(R.id.tv_tab_statistics)
        select = findViewById(R.id.select)

        tvDetails.setOnClickListener(this)
        tvStatistics.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_tab_details -> selectTab(0)
            R.id.tv_tab_statistics -> selectTab(1)
        }
    }

    fun selectTab(index: Int) {
        val tabWidth = tvDetails.width.toFloat()

        when (index) {
            0 -> {
                Log.d("FieldsTabView", "tes")
                select.animate().x(0f).setDuration(100).start()
                tvDetails.setTextColor(ContextCompat.getColor(context, R.color.text_primary_900))
                tvDetails.setTypeface(ResourcesCompat.getFont(context, R.font.lato_bold))
                tvStatistics.setTextColor(ContextCompat.getColor(context, R.color.text_600))
                tvStatistics.setTypeface(ResourcesCompat.getFont(context, R.font.lato_regular))
            }

            1 -> {
                select.animate().x(tabWidth).setDuration(100).start()
                tvDetails.setTextColor(ContextCompat.getColor(context, R.color.text_600))
                tvDetails.setTypeface(ResourcesCompat.getFont(context, R.font.lato_regular))
                tvStatistics.setTextColor(ContextCompat.getColor(context, R.color.text_primary_900))
                tvStatistics.setTypeface(ResourcesCompat.getFont(context, R.font.lato_bold))
            }
        }

        onTabSelectedListener?.invoke(index)
    }
}