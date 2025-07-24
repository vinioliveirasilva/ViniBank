package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

interface VerticalAlignmentComponentProperty {
    @Composable
    fun getVerticalAlignment(): Alignment.Vertical
    fun setVerticalAlignment(value: VerticalAlignmentOption)
}