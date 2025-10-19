package com.example.sawit.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sawit.R
import com.example.sawit.databinding.FragmentActivitiesBinding
import com.google.android.material.tabs.TabLayoutMediator

class ActivitiesFragment : Fragment() {

    private var _binding: FragmentActivitiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using view binding
        _binding = FragmentActivitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
        setupFabListener() // Set up the listener for the FAB
    }

    private fun setupViewPager() {
        // This part is a placeholder for now.
        // We will implement the ViewPager and its adapter in the next steps
        // to show the "Planned" and "Completed" lists.

        // val adapter = ActivityViewPagerAdapter(this)
        // binding.viewPager.adapter = adapter
        //
        // TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
        //     tab.text = when (position) {
        //         0 -> getString(R.string.planned) // We will add this string resource later
        //         1 -> getString(R.string.completed) // We will add this string resource later
        //         else -> null
        //     }
        // }.attach()
    }

    /**
     * Sets up the OnClickListener for the Floating Action Button.
     * When clicked, it will open the CreateEditActivity.
     */
    private fun setupFabListener() {
        binding.fabAddTask.setOnClickListener {
            // Create an Intent to navigate from the current context to CreateEditActivity
            val intent = Intent(requireActivity(), CreateEditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clean up the binding reference to avoid memory leaks
        _binding = null
    }
}

