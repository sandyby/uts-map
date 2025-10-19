package com.example.sawit.models

data class Field(
    val fieldId: Int,
    val fieldPhoto: String?,
    val fieldName: String,
    val fieldArea: Double?,
    val fieldLocation: String,
    val avgOilPalmAgeInMonths: Int,
    val oilPalmType: String,
    val fieldDesc: String,
    val notes: String
)
