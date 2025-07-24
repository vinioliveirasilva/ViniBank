package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

interface HorizontalAlignmentComponentProperty {
    @Composable
    fun getHorizontalAlignment(): Alignment.Horizontal
    fun setHorizontalAlignment(value: HorizontalAlignmentOptions)
}