package com.example.savey.ui.addTransaction.model

import com.example.savey.data.TransactionEntity
import com.example.savey.ui.home.model.TransactionType

data class TransactionDetails(
    val id: Int = 0,
    val type: TransactionType = TransactionType(),
    val merchant: String = "",
    val price: String = "",
    val time: String = ""
) {
    fun toTransactionEntity(): TransactionEntity = TransactionEntity(
        id = id,
        type = type.type,
        merchant = merchant,
        price = price.toDoubleOrNull() ?: 0.0,
        time = time
    )
}