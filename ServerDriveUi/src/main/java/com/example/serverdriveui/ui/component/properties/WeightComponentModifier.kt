package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

interface WeightComponentModifier {
    fun getWeight() : StateFlow<Float?>
    fun setWeight(value: Float)

    @get:Composable
    val ColumnScope.weightModifier: Modifier
}