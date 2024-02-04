package com.example.savey.data

import android.content.Context

interface AppContainer {
    val transactionsRepository: TransactionsRepository
}

class AppDataContainer(private val context: Context): AppContainer {
    override val transactionsRepository: TransactionsRepository by lazy {
        TransactionsRepositoryImpl(TransactionsDatabase.getDatabase(context).transactionDao())
    }
}