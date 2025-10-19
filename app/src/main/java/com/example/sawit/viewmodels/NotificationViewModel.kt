package com.example.sawit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationViewModel : ViewModel() {
    private val _notificationCount = MutableLiveData(0)
    val notificationCount: LiveData<Int> = _notificationCount

    fun increment() {
        _notificationCount.value = (_notificationCount.value ?: 0) + 1
    }

    fun reset() {
        _notificationCount.value = 0
    }

    fun setCount(count: Int) {
        _notificationCount.value = count
    }
}