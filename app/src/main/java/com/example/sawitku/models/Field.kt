package com.example.sawitku.models

data class Field(
    val fieldId: Int,
    val fieldName: String,
    val fieldArea: Double?,
    val avgOilPalmAgeInMonths: Int,
    val oilPalmType: String,
    val fieldDesc: String,
    val notes: String
)
