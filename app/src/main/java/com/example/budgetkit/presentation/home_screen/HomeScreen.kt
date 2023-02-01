package com.example.budgetkit.presentation.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.budgetkit.presentation.Screen
import com.example.budgetkit.presentation.home_screen.components.TopBar
import com.example.budgetkit.presentation.home_screen.components.TransactionsCategoryItem
import com.example.budgetkit.presentation.ui.theme.Typography

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getTransactions()
    }
    val state = viewModel.transactions
    val categorisedTransactionState = viewModel.categorisedTransaction
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            TopBar()
        }
        item {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Spent this week",
                    style = Typography.body1,
                    color = Color.Gray,
                )
                Text(text = "₹3600", style = Typography.h2)
            }
        }
/*        items(state) { transaction ->
            TransactionListItem(transaction = transaction, onItemClick = {
                navController.navigate(Screen.TransactionDetailScreen.route + "/${transaction.id}")
            })
        }*/
        items(categorisedTransactionState) {transactionCategory ->
            TransactionsCategoryItem(transactionCategory = transactionCategory, allTransactions = state, onItemClick = {
                navController.navigate(Screen.TransactionDetailScreen.route + "/${transactionCategory.categoryName}")
            })
        }
    }
}