package com.example.savey.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.savey.SaveyApplication
import com.example.savey.ui.addTransaction.TransactionEntryViewModel
import com.example.savey.ui.home.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            TransactionEntryViewModel(saveyApplication().container.transactionsRepository)
        }
        initializer {
            HomeViewModel(saveyApplication().container.transactionsRepository)
        }
    }

    private fun CreationExtras.saveyApplication(): SaveyApplication =
        (this[AndroidViewModelFactory.APPLICATION_KEY] as SaveyApplication)
}