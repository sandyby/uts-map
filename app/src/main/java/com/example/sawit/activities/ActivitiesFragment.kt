package com.example.sawit.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sawit.adapters.ActivityAdapter
import com.example.sawit.databinding.FragmentActivitiesBinding
import com.example.sawit.viewmodels.ActivityViewModel
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

class ActivitiesFragment : Fragment() {

    private var _binding: FragmentActivitiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ActivityViewModel by viewModels()
    private lateinit var activityAdapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupTabLayout()
        observeActivities()
        setupFabListener()
    }

    private fun setupFabListener() {
        binding.fabAddActivity.setOnClickListener {
            val intent = Intent(requireActivity(), CreateEditActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        activityAdapter = ActivityAdapter(
            onCheckboxClicked = { activity, isChecked ->
                val status = if (isChecked) "Completed" else "Planned"
                Toast.makeText(context, "${activity.fieldName} status changed to $status (Demo)", Toast.LENGTH_SHORT).show()
            },
            onEditClicked = { activity ->
                val intent = Intent(requireActivity(), CreateEditActivity::class.java).apply {
                    putExtra(CreateEditActivity.EXTRA_ACTIVITY, activity)
                }
                startActivity(intent)
            },
            onDeleteClicked = { activity ->
                Toast.makeText(context, "Delete ${activity.fieldName} (Demo)", Toast.LENGTH_SHORT).show()
            }
        )

        binding.recyclerViewActivities.apply {
            adapter = activityAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observeActivities() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.activities.collect { allActivities ->
                    if (binding.tabLayout.selectedTabPosition == 0) {
                        val plannedActivities = allActivities.filter { it.status == "planned" }
                        activityAdapter.submitList(plannedActivities)
                    } else {
                        val completedActivities = allActivities.filter { it.status == "completed" }
                        activityAdapter.submitList(completedActivities)
                    }
                }
            }
        }
    }

    private fun setupTabLayout() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val allActivities = viewModel.activities.value
                when (tab?.position) {
                    0 -> {
                        val plannedActivities = allActivities.filter { it.status == "planned" }
                        activityAdapter.submitList(plannedActivities)
                    }

                    1 -> {
                        val completedActivities = allActivities.filter { it.status == "completed" }
                        activityAdapter.submitList(completedActivities)
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}