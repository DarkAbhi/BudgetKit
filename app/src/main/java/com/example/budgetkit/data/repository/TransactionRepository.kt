package com.example.budgetkit.data.repository

import com.example.budgetkit.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun getTransactions(): Flow<List<Transaction>>
    suspend fun getTransactionsOfCategory(category:String): Flow<List<Transaction>>
    suspend fun getTransactionById(transactionId: String): Flow<Transaction>
    suspend fun insertTransactions(transactions: List<Transaction>)
}