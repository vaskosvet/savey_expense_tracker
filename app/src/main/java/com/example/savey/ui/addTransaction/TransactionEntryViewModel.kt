package com.example.savey.ui.addTransaction

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.savey.data.TransactionsRepository
import com.example.savey.ui.addTransaction.model.TransactionDetails
import com.example.savey.ui.addTransaction.model.TransactionUIState

class TransactionEntryViewModel(private val transactionsRepository: TransactionsRepository): ViewModel() {
    var transactionUIState by mutableStateOf(TransactionUIState())
        private set

    private fun validateInput(uiState: TransactionDetails = transactionUIState.transactionDetails): Boolean {
        return with(uiState) {
            type != 0 && price.isNotBlank()
        }
    }

    fun updateUIState(transactionDetails: TransactionDetails) {
        transactionUIState = TransactionUIState(transactionDetails = transactionDetails, isEntryValid = true) // TODO - validateInput(transactionDetails)
    }

    suspend fun saveTransaction() {
        if (validateInput()) {
            transactionsRepository.insertTransactionItem(transactionUIState.transactionDetails.toTransactionItem())
            transactionUIState = TransactionUIState()
        }
    }
}