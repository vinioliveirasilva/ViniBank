package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

interface HeightComponentProperty {
    fun getHeight(): StateFlow<Int?>
    fun setHeight(value: Int)

    @get:Composable
    val heightModifier: Modifier
}