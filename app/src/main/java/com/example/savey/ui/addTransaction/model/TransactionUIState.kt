package com.example.savey.ui.addTransaction.model

data class TransactionUIState(
    val transactionDetails: TransactionDetails = TransactionDetails(),
    val isEntryValid: Boolean = false
)
