package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Alignment
import kotlinx.coroutines.flow.StateFlow

interface ContentAlignmentComponentProperty {
    fun getContentAlignment(): StateFlow<Alignment>
    fun setContentAlignment(value: AlignmentOptions)
}