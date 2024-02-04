package com.example.savey.data

import kotlinx.coroutines.flow.Flow

class TransactionsRepositoryImpl(private val dao: TransactionDao): TransactionsRepository {
    override fun getAllTransactionsStream(): Flow<List<TransactionEntity>> = dao.getAllTransactions()

    override fun getTransactionStream(id: Int): Flow<TransactionEntity?> = dao.getTransaction(id)

    override suspend fun insertTransactionItem(transaction: TransactionEntity) = dao.insert(transaction)

    override suspend fun deleteTransaction(transaction: TransactionEntity) = dao.delete(transaction)

    override suspend fun updateTransaction(transaction: TransactionEntity) = dao.update(transaction)
}