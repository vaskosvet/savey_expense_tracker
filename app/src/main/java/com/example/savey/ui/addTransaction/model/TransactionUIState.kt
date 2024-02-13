package com.example.savey.ui.addTransaction.model

data class TransactionUIState(
    val transactionDetails: TransactionDetails = TransactionDetails(),
    val isEntryValid: Boolean = false,
    val isPopUpVisible: Boolean = false,
    val selectedTransactionText: String = ""
)