package com.vini.featurehome.contentprovider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainContentProvider : ContentProvider {
    override val title = "Home"
    override val selectedIcon = Icons.Filled.Home
    override val unselectedIcon = Icons.Outlined.Home
    override val content: @Composable (modifier: Modifier) -> Unit = { modifier ->
        Column(modifier = modifier) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) { AccountBalanceCardPreview() }
            Row { TransactionListPreview() }
        }
    }

    @Composable
    fun AccountBalanceCard(balance: String, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Current Balance",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = balance,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun AccountBalanceCardPreview() {
        MaterialTheme {
            AccountBalanceCard(balance = "$1,234.56")
        }
    }

    data class Transaction(
        val name: String,
        val date: String,
        val time: String,
        val amount: String,
        val isInput: Boolean
    )

    @Composable
    fun TransactionList(transactions: List<Transaction>, modifier: Modifier = Modifier) {
        Column(modifier = modifier.padding(16.dp)) {
            Text(
                text = "Recent Transactions",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            LazyColumn {
                items(transactions.size) { index ->
                    val transaction = transactions[index]
                    Card(
                        modifier = Modifier.padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = transaction.name,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                                Text(
                                    text = "${transaction.date} at ${transaction.time}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                            Column(
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = (if (transaction.isInput) "+" else "-") + transaction.amount,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (transaction.isInput)
                                        MaterialTheme.colorScheme.tertiary
                                    else
                                        MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun TransactionListPreview() {
        val sampleTransactions = listOf(
            Transaction("Grocery Store", "2025-05-10", "14:23", "45.67", isInput = false),
            Transaction("Salary", "2025-05-09", "09:00", "1,500.00", isInput = true),
            Transaction("Electric Bill", "2025-05-08", "16:45", "120.00", isInput = false)
        )
        MaterialTheme {
            TransactionList(transactions = sampleTransactions)
        }
    }
}