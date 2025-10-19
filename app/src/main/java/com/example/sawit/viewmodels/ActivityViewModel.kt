package com.example.sawit.viewmodels

import androidx.lifecycle.ViewModel
import com.example.sawit.models.Activity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Date

class ActivityViewModel : ViewModel() {
    private val _activities = MutableStateFlow<List<Activity>>(emptyList())
    val activities: StateFlow<List<Activity>> = _activities

    init {
        // Panggil fungsi untuk membuat data dummy
        loadHardcodedActivities()
    }

    private fun loadHardcodedActivities() {
        val dummyList = listOf(
            Activity(
                id = "1",
                fieldName = "Lahan 1",
                activityType = "Harvest",
                date = Date(),
                notes = "Pemanenan di blok A-3, fokus pada buah yang sudah matang.",
                status = "planned"
            ),
            Activity(
                id = "2",
                fieldName = "Lahan Manjur Sukses",
                activityType = "Fertilizing",
                date = Date(),
                notes = "Pemupukan dengan NPK seimbang di seluruh area.",
                status = "planned"
            ),
            Activity(
                id = "3",
                fieldName = "Lahan 1",
                activityType = "Pruning",
                date = Date(System.currentTimeMillis() - 86400000 * 2), // 2 hari yang lalu
                notes = "Pelepasan pelepah kering telah selesai.",
                status = "completed"
            ),
            Activity(
                id = "4",
                fieldName = "Kebun Belakang",
                activityType = "Scouting",
                date = Date(System.currentTimeMillis() - 86400000 * 5), // 5 hari yang lalu
                notes = "Pengecekan hama dan penyakit, tidak ditemukan anomali.",
                status = "completed"
            )
        )
        _activities.value = dummyList
    }
}

