package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

interface VerticalPaddingComponentProperty {
    @get:Composable
    val verticalPaddingModifier: Modifier
    fun getVerticalPadding(): StateFlow<Int>
    fun setVerticalPadding(padding: Int)
}