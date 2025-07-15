package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

interface HorizontalFillTypeComponentProperty {
    fun getHorizontalFillType(): StateFlow<HorizontalFillType>
    fun setHorizontalFillType(value: HorizontalFillType)
    @get:Composable
    val horizontalFillTypeModifier: Modifier
}