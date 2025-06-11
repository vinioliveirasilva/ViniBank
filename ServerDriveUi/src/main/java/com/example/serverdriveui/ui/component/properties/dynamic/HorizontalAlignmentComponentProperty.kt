package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.ui.Alignment
import kotlinx.coroutines.flow.StateFlow

interface HorizontalAlignmentComponentProperty {
    fun getHorizontalAlignment(): StateFlow<Alignment.Horizontal>
    fun setHorizontalAlignment(value: Alignment.Horizontal)
}