package com.example.sawit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sawit.R
class FieldsFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fields, container, false)
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
