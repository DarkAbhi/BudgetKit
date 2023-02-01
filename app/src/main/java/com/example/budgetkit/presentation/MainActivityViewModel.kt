package com.example.budgetkit.presentation

import androidx.lifecycle.ViewModel
import com.example.budgetkit.data.repository.TransactionRepositoryImpl
import com.example.budgetkit.models.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val transactionRepositoryImpl: TransactionRepositoryImpl,
) :
    ViewModel() {

    suspend fun addTransactions(transactions: List<Transaction>) {
        transactionRepositoryImpl.insertTransactions(transactions = transactions)
    }
}