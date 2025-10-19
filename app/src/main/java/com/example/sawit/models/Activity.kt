package com.example.sawit.models

import com.google.firebase.firestore.DocumentId
import java.util.Date

/**
 * Data model for an activity.
 * This structure is designed to be stored in Firestore.
 */
data class Activity(
    // This annotation automatically fills the 'id' field with the document ID from Firestore.
    @DocumentId
    val id: String = "",

    // We store fieldName directly for easy display in lists.
    val fieldName: String = "",

    // Type of activity, e.g., "Harvest", "Fertilizing".
    val activityType: String = "",

    // The date of the activity. Storing as Date for flexibility with Firestore.
    val date: Date = Date(),

    val notes: String = "",

    // Status to differentiate between "planned" and "completed" tasks.
    val status: String = "planned"
)

