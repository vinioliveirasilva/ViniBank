package com.vini.featurehome.contentprovider

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class CardContentProvider() : ContentProvider {
    override val content: @Composable (modifier: Modifier) -> Unit =
        { modifier -> Text("Card Content", modifier = modifier) }
    override val title = "Card"
    override val selectedIcon = Icons.Filled.Payment
    override val unselectedIcon = Icons.Outlined.Payment
}
