package com.example.savey.utils

import com.example.savey.R
import com.example.savey.ui.home.model.TransactionType
import com.example.savey.utils.Constants.Companion.DONATIONS
import com.example.savey.utils.Constants.Companion.GROCERIES
import com.example.savey.utils.Constants.Companion.HOME
import com.example.savey.utils.Constants.Companion.LIFESTYLE
import com.example.savey.utils.Constants.Companion.ONLINE_TRANSACTION
import com.example.savey.utils.Constants.Companion.POS_PAYMENT
import com.example.savey.utils.Constants.Companion.SHOPPING
import com.example.savey.utils.Constants.Companion.SPORT
import com.example.savey.utils.Constants.Companion.SUBSCRIPTION
import com.example.savey.utils.Constants.Companion.UTILITIES
import com.example.savey.utils.Constants.Companion.TRANSPORT

object TransactionUtils {
    fun getTransactionTypeDetails(type: Int): TransactionType {
        return TransactionType(
            type = type,
            stringRes = getTransactionTypeStringRes(type),
            imageRes = getTransactionTypeImageRes(type)
        )
    }
    private fun getTransactionTypeImageRes(type: Int): Int {
        return when (type) {
            ONLINE_TRANSACTION -> R.drawable.online_transactions
            POS_PAYMENT -> R.drawable.online_transactions
            SUBSCRIPTION -> R.drawable.type_subscription
            TRANSPORT -> R.drawable.type_transport
            GROCERIES -> R.drawable.type_groceries
            SHOPPING -> R.drawable.type_shopping
            UTILITIES -> R.drawable.type_utilities
            DONATIONS -> R.drawable.type_donations
            HOME -> R.drawable.type_utilities
            SPORT -> R.drawable.type_sport
            LIFESTYLE -> R.drawable.type_lifestyle
            else -> 0
        }
    }
    private fun getTransactionTypeStringRes(type: Int): Int {
        return when (type) {
            ONLINE_TRANSACTION -> R.string.online_transaction
            POS_PAYMENT -> R.string.pos_payment
            SUBSCRIPTION -> R.string.subscription
            TRANSPORT -> R.string.transport
            GROCERIES -> R.string.groceries
            SHOPPING -> R.string.shopping
            UTILITIES -> R.string.utilities
            DONATIONS -> R.string.donations
            HOME -> R.string.home
            SPORT -> R.string.sport
            LIFESTYLE -> R.string.lifestyle
            else -> 0
        }
    }
}