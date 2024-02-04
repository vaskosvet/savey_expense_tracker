package com.example.savey.ui.transaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.example.savey.ui.navigation.NavigationDestination
import com.example.savey.ui.transaction.model.TransactionDetails
import com.example.savey.ui.transaction.model.TransactionUIState
import kotlinx.coroutines.launch

object TransactionEntryDestination : NavigationDestination {
    override val route = "item_entry"
//    override val titleRes = R.string.item_entry_title
}

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
            // TODO
        }
    ) {
        TransactionEntryBody(
            modifier = Modifier.padding(it),
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
//        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(16.dp/*dimensionResource(id = R.dimen.padding_large)*/)
    ) {
        OutlinedTextField(
            value = transactionUIState.transactionDetails.price,
            onValueChange = { onTransactionValueChange(transactionUIState.transactionDetails.copy(price = it, type = 1)) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("price") }
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
