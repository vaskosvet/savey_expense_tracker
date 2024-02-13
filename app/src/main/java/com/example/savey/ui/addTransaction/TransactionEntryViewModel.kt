package com.example.savey.ui.addTransaction

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.savey.data.TransactionsRepository
import com.example.savey.ui.addTransaction.model.TransactionDetails
import com.example.savey.ui.addTransaction.model.TransactionUIState

class TransactionEntryViewModel(private val application: Application, private val transactionsRepository: TransactionsRepository): ViewModel() {
    var transactionUIState by mutableStateOf(TransactionUIState())
        private set

    fun updateUIState(transactionDetails: TransactionDetails) {
        transactionUIState = TransactionUIState(transactionDetails = transactionDetails, isEntryValid = true) // TODO - validateInput(transactionDetails)
    }

    fun onTransactionTypeSelected(type: Int, textRes: Int) {
        transactionUIState = transactionUIState.copy(
            isPopUpVisible = false,
            selectedTransactionText = getTransactionTypeText(resId = textRes),
            transactionDetails = transactionUIState.transactionDetails.copy(type = transactionUIState.transactionDetails.type.copy(type = type))
        )
    }

    fun changePopUpVisibility() {
        transactionUIState = transactionUIState.copy(isPopUpVisible = !transactionUIState.isPopUpVisible)
    }

    private fun getTransactionTypeText(resId: Int): String  = application.getString(resId)

    private fun validateInput(uiState: TransactionDetails = transactionUIState.transactionDetails): Boolean {
        return with(uiState) {
            type.type != 0 && price.isNotBlank() // TODO improve validation
        }
    }

    suspend fun saveTransaction() {
        if (validateInput()) {
            transactionsRepository.insertTransactionItem(transactionUIState.transactionDetails.toTransactionEntity()).also {
                if (it != -1L)
                    transactionUIState = TransactionUIState()
                else
                    println("Something went wrong. Please, try again.")
            }
        }
    }
}