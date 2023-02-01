package com.example.budgetkit.models

data class SmsModel(
    val id:String,
    val body:String,
    val address:String,
    val timestamp:Long
)
