package com.example.savey.ui.addTransaction

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.savey.ui.AppViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.savey.R
import com.example.savey.SaveyTopAppBar
import com.example.savey.utils.Constants
import com.example.savey.utils.Constants.Companion.TRANSACTION_ENTRY_ROUTE
import com.example.savey.ui.navigation.NavigationDestination
import com.example.savey.ui.addTransaction.model.TransactionDetails
import com.example.savey.ui.addTransaction.model.TransactionUIState
import kotlinx.coroutines.launch

object TransactionEntryDestination : NavigationDestination {
    override val route = TRANSACTION_ENTRY_ROUTE
    override val titleRes = R.string.enter_a_new_transaction
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
                title = stringResource(id = R.string.enter_a_new_transaction),
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
            },
            onTransactionTypeSelected = viewModel::onTransactionTypeSelected,
            changePopUpVisibility = viewModel::changePopUpVisibility
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionEntryBody(
    modifier: Modifier = Modifier,
    transactionUIState: TransactionUIState,
    onTransactionValueChange: (TransactionDetails) -> Unit,
    onSaveClick: () -> Unit,
    onTransactionTypeSelected: (Int, Int) -> Unit,
    changePopUpVisibility: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = transactionUIState.transactionDetails.price,
            onValueChange = {
                onTransactionValueChange(transactionUIState.transactionDetails.copy(price = it))
            },
            shape = MaterialTheme.shapes.large,
            placeholder = {
                Text(text = stringResource(id = R.string.example_price_zero))
            },
            label = {
                Text(text = stringResource(id = R.string.price))
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = transactionUIState.transactionDetails.merchant,
            onValueChange = {
                onTransactionValueChange(transactionUIState.transactionDetails.copy(merchant = it))
            },
            shape = MaterialTheme.shapes.large,
            placeholder = {
                Text(text = stringResource(id = R.string.example_merchant_amazon))
            },
            label = {
                Text(text = stringResource(id = R.string.merchant))
            }
        )
        ExposedDropdownMenuBox(
            expanded = transactionUIState.isPopUpVisible,
            onExpandedChange = {
                changePopUpVisibility()
            },
        ) {

            OutlinedTextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = transactionUIState.selectedTransactionText,
                onValueChange = {},
                label = {
                    Text(stringResource(id = R.string.choose_transaction_type), fontWeight = FontWeight.Bold)
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = transactionUIState.isPopUpVisible)
               },
                shape = MaterialTheme.shapes.large
            )

            ExposedDropdownMenu(
                expanded = transactionUIState.isPopUpVisible,
                onDismissRequest = {
                    changePopUpVisibility()
                },
            ) {
                Constants.transactionTypes.forEach { transactionType ->
                    val (type, stringResource) = transactionType
                    DropdownMenuItem(
                        text = { Text(stringResource(stringResource)) },
                        onClick = {
                            onTransactionTypeSelected(type, stringResource)
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSaveClick,
            enabled = transactionUIState.isEntryValid,
            shape = MaterialTheme.shapes.large
        ) {
            Text(text = stringResource(id = R.string.save).uppercase())
        }
    }
}