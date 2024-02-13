package com.example.savey.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savey.utils.Constants.Companion.transactionTypesMap
import com.example.savey.data.TransactionEntity
import com.example.savey.data.TransactionsRepository
import com.example.savey.ui.addTransaction.model.TransactionDetails
import com.example.savey.ui.home.model.HomeUIState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(transactionsRepository: TransactionsRepository): ViewModel() {
    var showBottomSheet by mutableStateOf(false)
        private set
    var chosenTransaction by mutableStateOf<TransactionDetails?>(null)
        private set

    val homeUIState: StateFlow<HomeUIState> =
        transactionsRepository.getAllTransactionsStream().map { list ->
            HomeUIState(list.map { it.toTransactionDetails() })
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HomeUIState()
        )

    fun closeBottomSheet() {
        showBottomSheet = false
    }

    fun onTransactionClicked(transaction: TransactionDetails) {
        chosenTransaction = transaction
        showBottomSheet = true
    }
}