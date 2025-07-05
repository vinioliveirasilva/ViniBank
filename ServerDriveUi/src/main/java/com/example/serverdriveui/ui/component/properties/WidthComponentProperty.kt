package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

interface WidthComponentProperty {
    fun getWidth(): StateFlow<Int?>
    fun setWidth(value: Int)

    @get:Composable
    val widthModifier: Modifier
}