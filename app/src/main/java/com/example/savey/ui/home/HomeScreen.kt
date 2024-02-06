package com.example.savey.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.savey.R
import com.example.savey.SaveyTopAppBar
import com.example.savey.UtilsConstants.Companion.HOME_ROUTE
import com.example.savey.data.TransactionEntity
import com.example.savey.ui.AppViewModelProvider
import com.example.savey.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = HOME_ROUTE
    override val titleRes = R.string.home
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToTransactionEntry: () -> Unit,
    navigateToTransactionUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val homeUIState by viewModel.homeUIState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val sheetState = rememberModalBottomSheetState()
    Scaffold(
        modifier = modifier.padding(16.dp),
        topBar = {
            SaveyTopAppBar(
                title = stringResource(id = R.string.home),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navigateToTransactionEntry() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        if (viewModel.showBottomSheet)
            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = viewModel::closeBottomSheet
            ) {
                viewModel.chosenTransaction?.let {
                    BottomSheetContent(transaction = it)
                }
            }

        TransactionsList(
            modifier = Modifier.padding(paddingValues),
            transactionsList = homeUIState.itemList,
            onItemClick = viewModel::onTransactionClicked
        )
    }
}


@Composable
private fun TransactionsList(
    transactionsList: List<TransactionEntity>,
    onItemClick: (TransactionEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(items = transactionsList, key = { it.id }) { transaction ->
            TransactionItem(transaction = transaction,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .clickable { onItemClick(transaction) })
        }
    }
}

@Composable
private fun TransactionItem(
    transaction: TransactionEntity,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_account_circle),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .padding(end = 8.dp)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(R.string.online_transaction).plus(":")) // Todo add transaction type
                Text(modifier = Modifier.align(Alignment.End),text = transaction.price.toString())
                if (transaction.merchant.isNotBlank())
                    Text(text = "${stringResource(id = R.string.merchant)}: ${transaction.merchant}") // TODO
            }
        }

    }
}

@Composable
fun BottomSheetContent(transaction: TransactionEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_account_circle),
            contentDescription = null,
            alignment = Alignment.Center
        ) // TODO change image after changing transaction types.
        KeyValueRow(
            key = stringResource(id = R.string.price),
            value = transaction.price.toString()
        )
        KeyValueRow(
            key = stringResource(id = R.string.merchant),
            value = transaction.merchant
        )
    }
}

@Composable
fun KeyValueRow(
    key: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = key)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = value)
    }
}