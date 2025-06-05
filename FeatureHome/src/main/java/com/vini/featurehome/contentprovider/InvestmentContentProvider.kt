package com.vini.featurehome.contentprovider

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class InvestmentContentProvider : ContentProvider {
    override val content: @Composable (modifier: Modifier) -> Unit =
        { modifier -> Text("Investment Content", modifier = modifier) }
    override val title = "Investment"
    override val selectedIcon = Icons.Filled.BarChart
    override val unselectedIcon = Icons.Outlined.BarChart
}