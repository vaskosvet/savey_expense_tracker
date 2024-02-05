package com.example.savey.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.savey.data.TransactionsRepository
import com.example.savey.ui.home.model.HomeUIState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(transactionsRepository: TransactionsRepository): ViewModel() {

    val homeUIState: StateFlow<HomeUIState> =
        transactionsRepository.getAllTransactionsStream().map {
            HomeUIState(it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HomeUIState()
        )
}