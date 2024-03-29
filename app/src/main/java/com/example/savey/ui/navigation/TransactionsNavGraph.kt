package com.example.savey.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.savey.ui.addTransaction.TransactionEntryDestination
import com.example.savey.ui.addTransaction.TransactionEntryScreen
import com.example.savey.ui.home.HomeDestination
import com.example.savey.ui.home.HomeScreen

@Composable
fun TransactionsNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(TransactionEntryDestination.route) {
            TransactionEntryScreen(
                navigateBack = navController::popBackStack,
                onNavigateUp = navController::navigateUp
            )
        }
        composable(HomeDestination.route) {
            HomeScreen(
                navigateToTransactionEntry = { navController.navigate(TransactionEntryDestination.route) },
                navigateToTransactionUpdate = { /*TODO*/ }
            )
        }
    }
}