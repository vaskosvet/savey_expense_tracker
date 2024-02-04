package com.example.savey.ui.transaction.model

data class TransactionUIState(
    val transactionDetails: TransactionDetails = TransactionDetails(),
    val isEntryValid: Boolean = false
)
