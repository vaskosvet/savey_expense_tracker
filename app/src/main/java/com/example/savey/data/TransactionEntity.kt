package com.example.savey.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.savey.ui.addTransaction.model.TransactionDetails
import com.example.savey.ui.addTransaction.model.TransactionUIState
import com.example.savey.utils.TransactionUtils
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

    fun toTransactionDetails(): TransactionDetails = TransactionDetails(
        id = id,
        type = TransactionUtils.getTransactionTypeDetails(type),
        merchant = merchant,
        price = price.toString(),
        time = time
    )
}