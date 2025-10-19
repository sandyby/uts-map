package com.example.sawit.utils

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.sawit.R

//class FieldsTabView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs),
//    View.OnClickListener {
//    private lateinit var tvDetailsTabTitle: TextView
//    private lateinit var tvStatisticsTabTitle: TextView
//    private lateinit var select: TextView
//    private lateinit var def: ColorStateList
//
//    init {
//        LayoutInflater.from(context).inflate(R.layout.fields_tab, this, true)
//
//        // Find views
//        tvDetailsTabTitle = findViewById(R.id.tv_tab_details)
//        tvStatisticsTabTitle = findViewById(R.id.tv_tab_statistics)
//        select = findViewById(R.id.select)
//
//        def = tvStatisticsTabTitle.textColors
//
//        tvDetailsTabTitle.setOnClickListener(this)
//        tvStatisticsTabTitle.setOnClickListener(this)
//    }
////
////    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
////        super.onCreate(savedInstanceState, persistentState)
////        val view = LayoutInflater.from(applicationContext).inflate(R.layout.)
////        setContentView(R.layout.fi)
////
////        tvDetailsTabTitle = findViewById(R.det)
////
////    }
//
//    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.tv_tab_details -> {
//                select.animate().x(0f).setDuration(100).start()
//                tvDetailsTabTitle.setTextColor(Color.WHITE)
//                tvStatisticsTabTitle.setTextColor(def)
//            }
//
//            R.id.tv_tab_statistics -> {
//                val offset = tvDetailsTabTitle.width.toFloat()
//                select.animate().x(offset).setDuration(100).start()
//                tvDetailsTabTitle.setTextColor(def)
//                tvStatisticsTabTitle.setTextColor(Color.WHITE)
//            }
//        }
//    }
//}

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