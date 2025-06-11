package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.flow.StateFlow

interface TextAlignComponentProperty {
    fun getTextAlign(): StateFlow<TextAlign>
    fun setTextAlign(textAlign: TextAlign)
}