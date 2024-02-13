package com.example.savey.ui.home.model

import com.example.savey.data.TransactionEntity
import com.example.savey.ui.addTransaction.model.TransactionDetails

data class HomeUIState(
//    val itemList: List<TransactionEntity> = listOf()
    val itemList: List<TransactionDetails> = listOf()
)