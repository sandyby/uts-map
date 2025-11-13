package com.example.sawit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.sawit.R
import com.example.sawit.adapters.PredictionsPagerAdapter
import com.example.sawit.utils.PredictionTabView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class PredictFragment : Fragment(R.layout.fragment_predictions) {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val view = inflater.inflate(R.layout.fragment_predictions, container, false)
//
//        layoutHarvest = view.findViewById(R.id.layout_harvest)
//        layoutCondition = view.findViewById(R.id.layout_condition)
//        val ptvPredictions = view.findViewById<PredictionTabView>(R.id.ptvPredictions)
//
//        showHarvest()
//
//        ptvPredictions.onTabSelectedListener = { index ->
//            when (index) {
//                0 -> showHarvest()
//                1 -> showCondition()
//            }
//        }
//
//        return view
//    }

//    private fun showHarvest() {
//        layoutHarvest.visibility = View.VISIBLE
//        layoutCondition.visibility = View.GONE
//    }
//
//    private fun showCondition() {
//        layoutHarvest.visibility = View.GONE
//        layoutCondition.visibility = View.VISIBLE
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tabLayout = view.findViewById(R.id.tl_predictions)
        viewPager = view.findViewById(R.id.vp_predictions)

        val adapter = PredictionsPagerAdapter(requireActivity())
        viewPager.adapter = adapter

        // Link TabLayout with ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Harvest"
                1 -> "Condition"
                else -> ""
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { viewPager.currentItem = it.position }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
}
