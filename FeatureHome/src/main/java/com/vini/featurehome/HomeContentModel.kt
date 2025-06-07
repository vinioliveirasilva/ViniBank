package com.vini.featurehome

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class HomeContentModel(
    val content: @Composable (Modifier) -> Unit,
    val titles: List<String>,
    val icons: List<ImageVector>,
)