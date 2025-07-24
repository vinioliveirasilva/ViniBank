package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface WeightComponentModifier {
    @get:Composable
    val ColumnScope.weightModifier: Modifier

    @get:Composable
    val RowScope.weightModifier: Modifier
}