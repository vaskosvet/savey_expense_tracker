package com.example.savey.ui.home.model

import com.example.savey.data.TransactionEntity

data class HomeUIState(
    val itemList: List<TransactionEntity> = listOf()
)
