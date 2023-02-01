package com.example.budgetkit.presentation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("home_screen")
    object TransactionCategoryScreen : Screen("transaction_category_screen")
    object TransactionDetailScreen : Screen("transaction_detail_screen")
}