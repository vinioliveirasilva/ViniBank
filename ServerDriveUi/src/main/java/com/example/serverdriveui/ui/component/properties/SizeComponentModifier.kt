package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface SizeComponentModifier {
    @get:Composable
    val sizeModifier: Modifier

    @Composable
    fun getSize() : Int?
}