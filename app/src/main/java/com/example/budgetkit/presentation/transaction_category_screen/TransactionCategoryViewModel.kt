package com.example.budgetkit.presentation.transaction_category_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetkit.data.repository.TransactionRepositoryImpl
import com.example.budgetkit.models.Transaction
import com.example.budgetkit.presentation.home_screen.TransactionCategory
import kotlinx.coroutines.launch
import javax.inject.Inject

class TransactionCategoryViewModel @Inject constructor(
    private val transactionRepositoryImpl: TransactionRepositoryImpl,
) : ViewModel() {

    var transactions by mutableStateOf(emptyList<Transaction>())

    fun getTransactionsOfCategory(category: String) {
        viewModelScope.launch {
            transactionRepositoryImpl.getTransactionsOfCategory(category = category)
                .collect {
                    transactions = it
                }
        }
    }
}