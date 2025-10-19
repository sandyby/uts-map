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

class PredictionTabView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private val tvHarvest: TextView
    private val tvCondition: TextView
    private val select: TextView
    var onTabSelectedListener: ((tabIndex: Int) -> Unit)? = null

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.predictions_tab, this, true)

        tvHarvest = findViewById(R.id.tv_tab_harvest)
        tvCondition = findViewById(R.id.tv_tab_condition)
        select = findViewById(R.id.select)

        tvHarvest.setOnClickListener(this)
        tvCondition.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_tab_harvest -> selectTab(0)
            R.id.tv_tab_condition -> selectTab(1)
        }
    }

    fun selectTab(index: Int) {
        val tabWidth = tvHarvest.width.toFloat()

        when (index) {
            0 -> {
                Log.d("PredictionTabView", "tes")
                select.animate().x(0f).setDuration(100).start()
                tvHarvest.setTextColor(ContextCompat.getColor(context, R.color.text_primary_900))
                tvHarvest.setTypeface(ResourcesCompat.getFont(context, R.font.lato_bold))
                tvCondition.setTextColor(ContextCompat.getColor(context, R.color.text_600))
                tvCondition.setTypeface(ResourcesCompat.getFont(context, R.font.lato_regular))
            }

            1 -> {
                select.animate().x(tabWidth).setDuration(100).start()
                tvHarvest.setTextColor(ContextCompat.getColor(context, R.color.text_600))
                tvHarvest.setTypeface(ResourcesCompat.getFont(context, R.font.lato_regular))
                tvCondition.setTextColor(ContextCompat.getColor(context, R.color.text_primary_900))
                tvCondition.setTypeface(ResourcesCompat.getFont(context, R.font.lato_bold))
            }
        }

        onTabSelectedListener?.invoke(index)
    }
}