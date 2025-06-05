package com.vini.featurehome.contentprovider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

interface ContentProvider {
    val content: @Composable (Modifier) -> Unit
    val title: String
    val selectedIcon: ImageVector
    val unselectedIcon: ImageVector
}