package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

interface HorizontalPaddingComponentProperty {
    @get:Composable
    val horizontalPaddingModifier: Modifier
    fun getHorizontalPadding(): StateFlow<Int>
    fun setHorizontalPadding(padding: Int)
}