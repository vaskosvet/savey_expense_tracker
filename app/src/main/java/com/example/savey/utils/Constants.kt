package com.example.savey.utils

import com.example.savey.R

class Constants {
    companion object {
        /** Navigation Routes */
        const val HOME_ROUTE = "home"
        const val TRANSACTION_ENTRY_ROUTE = "transaction_entry"

        /** Transaction Types */
        const val ONLINE_TRANSACTION = 1
        const val POS_PAYMENT = 2
        const val SUBSCRIPTION = 3
        const val TRANSPORT = 4
        const val GROCERIES = 5
        const val SHOPPING = 6
        const val UTILITIES = 7
        const val DONATIONS = 8
        const val HOME = 9
        const val SPORT = 10
        const val LIFESTYLE = 11

        val transactionTypesMap: Map<Int, Pair<Int, Int>> = mapOf(
            ONLINE_TRANSACTION to (R.drawable.online_transactions to R.string.online_transaction),
            POS_PAYMENT to (R.drawable.online_transactions to R.string.pos_payment),
            SUBSCRIPTION to (R.drawable.type_subscription to R.string.subscription),
            TRANSPORT to (R.drawable.type_transport to R.string.transport),
            GROCERIES to (R.drawable.type_groceries to R.string.groceries),
            SHOPPING to (R.drawable.type_shopping to R.string.shopping),
            UTILITIES to (R.drawable.type_utilities to R.string.utilities),
            DONATIONS to (R.drawable.type_donations to R.string.donations),
            HOME to (R.drawable.type_utilities to R.string.home),
            SPORT to (R.drawable.type_sport to R.string.sport),
            LIFESTYLE to (R.drawable.type_lifestyle to R.string.lifestyle)
        )

        val transactionTypes: List<Pair<Int, Int>> = listOf(
            ONLINE_TRANSACTION to R.string.online_transaction,
            POS_PAYMENT to R.string.pos_payment,
            SUBSCRIPTION to R.string.subscription,
            TRANSPORT to R.string.transport,
            GROCERIES to R.string.groceries,
            SHOPPING to R.string.shopping,
            UTILITIES to R.string.utilities,
            DONATIONS to R.string.donations,
            HOME to R.string.home,
            SPORT to R.string.sport,
            LIFESTYLE to R.string.lifestyle
        )
    }
}