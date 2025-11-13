package com.example.sawit.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sawit.fragments.PredictionsFragment
import com.example.sawit.fragments.PrediksiFormFragment

class PredictionsPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PredictionsFragment()
            1 -> PrediksiFormFragment()
            else -> PredictionsFragment()
        }
    }
}