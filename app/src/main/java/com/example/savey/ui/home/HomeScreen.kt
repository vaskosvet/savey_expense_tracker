package com.example.savey.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.savey.R
import com.example.savey.SaveyTopAppBar
import com.example.savey.data.TransactionEntity
import com.example.savey.ui.AppViewModelProvider
import com.example.savey.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
//    override val titleRes = R.string.app_name
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

    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            SaveyTopAppBar(
                title = "Home",
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        TransactionsList(
            modifier = Modifier.padding(it),
            transactionsList = homeUIState.itemList,
            onItemClick = { it.id}
        )
    }
}


@Composable
private fun TransactionsList(
    transactionsList: List<TransactionEntity>, onItemClick: (TransactionEntity) -> Unit, modifier: Modifier = Modifier
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
    transaction: TransactionEntity, modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clip(CircleShape)
                    .padding(end = 8.dp)
            )

            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Online Transaction")
                Text(modifier = Modifier.align(Alignment.End),text = transaction.price.toString())
                if (transaction.merchant.isNotBlank())
                    Text(text = "Merchant: ${transaction.merchant}")
            }
        }

    }/* ElevatedCard(modifier = Modifier.fillMaxWidth(.5f)) {
        Row {
            Text(text = "Price:")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = transaction.price.toString())
        }
        if (transaction.merchant.isNotBlank())
            Text(text = "Merchant: ${transaction.merchant}")
    }*/
//    Card(
//        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
//            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = item.name,
//                    style = MaterialTheme.typography.titleLarge,
//                )
//                Spacer(Modifier.weight(1f))
//                Text(
//                    text = item.formatedPrice(),
//                    style = MaterialTheme.typography.titleMedium
//                )
//            }
//            Text(
//                text = stringResource(R.string.in_stock, item.quantity),
//                style = MaterialTheme.typography.titleMedium
//            )
//        }
//    }
}