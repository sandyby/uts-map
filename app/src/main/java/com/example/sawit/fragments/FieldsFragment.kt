package com.example.sawit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sawit.R
import com.example.sawit.adapters.FieldsDashboardAdapter
import com.example.sawit.adapters.FieldsFieldsAdapter
import com.example.sawit.databinding.FragmentFieldsBinding
import com.example.sawit.utils.VerticalSpaceItemDecoration
import com.example.sawit.viewmodels.FieldViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FieldsFragment : Fragment() {
    private var _binding: FragmentFieldsBinding? = null
    private val binding get() = _binding!!
    private val fieldViewModel: FieldViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFieldsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FieldsFieldsAdapter(
            onClick = { field ->
                val action = FieldsDetailFragment.newInstance(field.fieldId)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fl_scroll_view_content, action)
                    .addToBackStack(null)
                    .commit()
            },
            onDeleteClick = { field ->
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Delete Field")
                    .setMessage("Are you sure you want to delete '${field.fieldName}'?")
                    .setPositiveButton("Delete") { dialog, _ ->
//                        fieldViewModel.deleteField(field.fieldId)
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setCancelable(true)
                    .show()
            }
            //            childFragmentManager.beginTransaction()
//                .replace(R.id.fl_fieldsFields, action)

            //                .addToBackStack(null).commitAllowingStateLoss()
            //            Toast.makeText(
//                context,
//                "clicked: ${field.fieldName} dari fields page",
//                Toast.LENGTH_SHORT
//            ).show()
        )

        binding.rvFieldsFields.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        val spacingInPx = resources.getDimensionPixelSize(R.dimen.vertical_item_spacing)
        binding.rvFieldsFields.addItemDecoration(VerticalSpaceItemDecoration(spacingInPx))

        viewLifecycleOwner.lifecycleScope.launch {
            fieldViewModel.fieldsData.collectLatest { fields ->
                adapter.submitList(fields)
            }
        }

    }
}


//class HomeFragment : Fragment() {
//    private var _binding: FragmentHomeBinding? = null
//    private val binding get() = _binding!!
//    private val fieldViewModel: FieldViewModel by viewModels()
//    private val notificationViewModel: NotificationViewModel by activityViewModels()
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        _binding = FragmentHomeBinding.inflate(inflater, container, false)
////        val view = inflater.inflate(R.layout.fragment_home, container, false)
////        val composeView = view.findViewById<ComposeView>(R.id.cv_notification_badge)
//
//        val composeView = binding.cvNotificationBadge
//        composeView.setContent {
//            val notificationCount by notificationViewModel.notificationCount.observeAsState(0)
//
////            var notificationCount by remember { mutableIntStateOf(0) }
//
//            NotificationIconWithBadge(
//                count = notificationCount,
//                onClick = {
//                    notificationViewModel.increment()
//                    Log.d("HomeFragment", "Notification icon clicked — count = $notificationCount")
//                }
//            )
//        }
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val adapter = FieldsAdapter { field ->
//            Toast.makeText(context, "clicked: ${field.fieldName}", Toast.LENGTH_SHORT).show()
//        }
//
//
//        binding.rvFieldsOverview.apply {
//            this.adapter = adapter
//            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        }
//        val spacingInPx = resources.getDimensionPixelSize(R.dimen.horizontal_item_spacing)
//        binding.rvFieldsOverview.addItemDecoration(HorizontalSpaceItemDecoration(spacingInPx))
//
//        viewLifecycleOwner.lifecycleScope.launch {
//            fieldViewModel.fieldsData.collectLatest { fields ->
//                adapter.submitList(fields)
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
