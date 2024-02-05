package com.example.savey.ui.addTransaction.model

import com.example.savey.data.TransactionEntity

data class TransactionDetails(
    val id: Int = 1,
    val type: Int = 0,
    val merchant: String = "",
    val price: String = "",
    val time: String = ""
) {
    fun toTransactionItem(): TransactionEntity = TransactionEntity(
        id = id,
        type = type,
        merchant = merchant,
        price = price.toDoubleOrNull() ?: 0.0,
        time = time
    )
}
