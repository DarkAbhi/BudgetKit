package com.example.budgetkit.models

import androidx.room.Entity

@Entity(tableName = "transactions")
data class Transaction(
    private val amountSpent: Double,
    private val category: String,
    private val timestamp: Long,
)
