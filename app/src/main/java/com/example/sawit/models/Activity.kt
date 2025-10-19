package com.example.sawit.models

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Activity(
    @DocumentId
    val id: String = "",
    val fieldName: String = "",
    val activityType: String = "",
    val date: Date = Date(),
    val notes: String = "",
    val status: String = "planned"
) : Parcelable

