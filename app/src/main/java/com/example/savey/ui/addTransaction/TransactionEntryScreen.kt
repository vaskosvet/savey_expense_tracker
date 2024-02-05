package com.example.savey.ui.addTransaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.savey.ui.AppViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.savey.SaveyTopAppBar
import com.example.savey.ui.navigation.NavigationDestination
import com.example.savey.ui.addTransaction.model.TransactionDetails
import com.example.savey.ui.addTransaction.model.TransactionUIState
import kotlinx.coroutines.launch

object TransactionEntryDestination : NavigationDestination {
    override val route = "transaction_entry"
//    override val titleRes = R.string.item_entry_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: TransactionEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            SaveyTopAppBar(
                title = "Add a transaction",
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        }
    ) {
        TransactionEntryBody(
            modifier = Modifier
                .padding(it)
                .padding(16.dp),
            transactionUIState = viewModel.transactionUIState,
            onTransactionValueChange = viewModel::updateUIState,
            onSaveClick = {
                scope.launch {
                    viewModel.saveTransaction()
                }
            }
        )
    }
}



@Composable
fun TransactionEntryBody(
    transactionUIState: TransactionUIState,
    onTransactionValueChange: (TransactionDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp/*dimensionResource(id = R.dimen.padding_large)*/)) {
        OutlinedTextField(
            value = transactionUIState.transactionDetails.price,
            onValueChange = { onTransactionValueChange(transactionUIState.transactionDetails.copy(price = it, type = 1)) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("price") }
        )

        OutlinedTextField(
            value = transactionUIState.transactionDetails.merchant,
            onValueChange = { onTransactionValueChange(transactionUIState.transactionDetails.copy(merchant = it)) },
            modifier = Modifier.fillMaxWidth(),
            label   = { Text(text = "Merchant") }
        )

        Button(
            onClick = onSaveClick,
            enabled = transactionUIState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "save")
        }
    }
}
