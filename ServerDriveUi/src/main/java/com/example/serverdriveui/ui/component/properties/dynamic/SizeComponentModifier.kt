package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

interface SizeComponentModifier {
    @get:Composable
    val sizeModifier: Modifier
    fun getSize() : StateFlow<Int?>
    fun setSize(size: Int)
}