package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

interface VerticalFillTypeComponentProperty {
    fun getVerticalFillType(): StateFlow<VerticalFillType>
    fun setVerticalFillType(value: VerticalFillType)
    @get:Composable
    val verticalFillTypeModifier: Modifier
}