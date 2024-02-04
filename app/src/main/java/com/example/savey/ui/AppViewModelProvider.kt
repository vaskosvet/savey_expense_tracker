package com.example.savey.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.savey.TransactionsApplication
import com.example.savey.ui.transaction.TransactionEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            TransactionEntryViewModel(transactionsApplication().container.transactionsRepository)
        }
    }

    private fun CreationExtras.transactionsApplication(): TransactionsApplication =
        (this[AndroidViewModelFactory.APPLICATION_KEY] as TransactionsApplication)
}