package com.example.budgetkit.presentation.transaction_category_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetkit.presentation.ui.theme.Typography

@Composable
fun TransactionCategoryScreen(
    viewModel: TransactionCategoryViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.getTransactionsOfCategory()
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "New stuff",
            style = Typography.body1,
            color = Color.Gray,
        )
        Text(text = "₹3600", style = Typography.h2)
    }
}