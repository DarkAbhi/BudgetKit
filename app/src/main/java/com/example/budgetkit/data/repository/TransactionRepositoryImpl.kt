package com.example.budgetkit.data.repository

import com.example.budgetkit.data.room.dao.TransactionDao
import com.example.budgetkit.models.Transaction
import kotlinx.coroutines.flow.Flow

class TransactionRepositoryImpl(
    private val transactionDao: TransactionDao,
) : TransactionRepository {

    override suspend fun getTransactions() = transactionDao.getAllTransactions()

    override suspend fun getTransactionsOfCategory(category:String) = transactionDao.getAllTransactionsOfCategory(category = category)

    override suspend fun getTransactionById(transactionId: String) = transactionDao.getTransaction(transactionId)

    override suspend fun insertTransactions(transactions: List<Transaction>) = transactionDao.insertTransactions(transactions)

}