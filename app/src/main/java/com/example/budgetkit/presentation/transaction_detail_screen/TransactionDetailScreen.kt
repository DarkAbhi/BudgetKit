package com.example.budgetkit.presentation.transaction_detail_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.budgetkit.presentation.home_screen.HomeScreenViewModel
import com.example.budgetkit.presentation.ui.theme.Typography

@Composable
fun TransactionDetailScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
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