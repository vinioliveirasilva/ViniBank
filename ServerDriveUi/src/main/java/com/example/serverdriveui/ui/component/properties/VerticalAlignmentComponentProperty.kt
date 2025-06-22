package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Alignment
import kotlinx.coroutines.flow.StateFlow

interface VerticalAlignmentComponentProperty {
    fun getVerticalAlignment(): StateFlow<Alignment.Vertical>
    fun setVerticalAlignment(value: Alignment.Vertical)
}