package com.example.budgetkit.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey val id:String,
    val amountSpent: Double,
    val category: String,
    val timestamp: Long,
    val sms: String
)
