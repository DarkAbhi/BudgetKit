package com.example.budgetkit.presentation.home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetkit.data.repository.TransactionRepositoryImpl
import com.example.budgetkit.models.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val transactionRepositoryImpl: TransactionRepositoryImpl,
) : ViewModel() {

    var transactions by mutableStateOf(emptyList<Transaction>())
    var categorisedTransaction by mutableStateOf(emptyList<TransactionCategory>())

    fun getTransactions() {
        viewModelScope.launch {
            transactionRepositoryImpl.getTransactions().collect { data ->
                categorisedTransaction = data.groupBy {
                    it.category
                }.map {
                    TransactionCategory(it.key, it.value.sumOf { transaction->
                        transaction.amountSpent
                    }, it.value.size)
                }
                transactions = data
            }
        }
    }
}

data class TransactionCategory(
    val categoryName: String,
    val amount: Double,
    val totalTransactions: Int,
)