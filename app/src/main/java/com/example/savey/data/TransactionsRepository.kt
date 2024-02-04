package com.example.savey.data

import kotlinx.coroutines.flow.Flow

interface TransactionsRepository {
    fun getAllTransactionsStream(): Flow<List<TransactionEntity>>

    fun getTransactionStream(id: Int): Flow<TransactionEntity?>

    suspend fun insertTransactionItem(transaction: TransactionEntity)

    suspend fun deleteTransaction(transaction: TransactionEntity)

    suspend fun updateTransaction(transaction: TransactionEntity)
}