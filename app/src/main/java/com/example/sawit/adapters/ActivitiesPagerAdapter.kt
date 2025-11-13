package com.example.sawit.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.sawit.fragments.ActivitiesFragment
import com.example.sawit.fragments.CompletedActivitiesFragment
import com.example.sawit.fragments.PlannedActivitiesFragment
import com.example.sawit.fragments.PredictionsFragment
import com.example.sawit.fragments.PrediksiFormFragment

class ActivitiesPagerAdapter(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> PlannedActivitiesFragment()
            1 -> CompletedActivitiesFragment()
            else -> PlannedActivitiesFragment()
        }
    }
}