package com.example.sawitku.models

import java.util.Date

data class Activity(
    val fieldId: Int,
    val task: String,
    val date: Date,
    val notes: String
)
