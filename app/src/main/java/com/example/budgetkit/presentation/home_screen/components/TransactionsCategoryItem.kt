package com.example.budgetkit.presentation.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.budgetkit.models.Transaction
import com.example.budgetkit.presentation.home_screen.TransactionCategory

@Composable
fun TransactionsCategoryItem(
    transactionCategory: TransactionCategory,
    allTransactions:List<Transaction>,
    onItemClick: (TransactionCategory) -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick(transactionCategory) }
        .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${transactionCategory.categoryName} ${transactionCategory.amount} ${transactionCategory.totalTransactions}",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
    }
}