package com.example.savey.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.savey.ui.transaction.TransactionEntryDestination
import com.example.savey.ui.transaction.TransactionEntryScreen

@Composable
fun TransactionsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = TransactionEntryDestination.route,
        modifier = modifier
    ) {
        composable(TransactionEntryDestination.route) {
            TransactionEntryScreen(navigateBack = { /*TODO*/ }, onNavigateUp = { /*TODO*/ })
        }
    }
}