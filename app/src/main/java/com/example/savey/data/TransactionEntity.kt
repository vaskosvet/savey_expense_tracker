package com.example.savey.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.savey.ui.transaction.model.TransactionDetails
import com.example.savey.ui.transaction.model.TransactionUIState
import java.text.NumberFormat

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: Int,
    val merchant: String,
    val price: Double,
    val time: String
) {
    fun toTransactionUIState(isEntryValid: Boolean = false): TransactionUIState = TransactionUIState(
        transactionDetails = toTransactionDetails(),
        isEntryValid = isEntryValid
    )

    fun formattedPrice(): String {
        return NumberFormat.getCurrencyInstance().format(price)
    }

    private fun toTransactionDetails(): TransactionDetails = TransactionDetails(
        id = id,
        type = type,
        merchant = merchant,
        price = price.toString(),
        time = time
    )
}