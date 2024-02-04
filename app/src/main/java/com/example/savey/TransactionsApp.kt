package com.example.savey

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.savey.ui.navigation.TransactionsNavHost

@Composable
fun TransactionsApp(navController: NavHostController = rememberNavController()) {
    TransactionsNavHost(navController)

}