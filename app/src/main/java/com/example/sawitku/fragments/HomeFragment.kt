package com.example.sawitku.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.sawitku.R
import com.example.sawitku.ui.NotificationIconWithBadge
import com.example.sawitku.viewmodels.NotificationViewModel

class HomeFragment : Fragment() {
    private val notificationViewModel: NotificationViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.cv_notification_badge)

        composeView.setContent {
            val notificationCount by notificationViewModel.notificationCount.observeAsState(0)

//            var notificationCount by remember { mutableIntStateOf(0) }

            NotificationIconWithBadge(
                count = notificationCount,
                onClick = {
                    notificationViewModel.increment()
                    Log.d("HomeFragment", "Notification icon clicked — count = $notificationCount")
                }
            )
        }

        return view
    }
}