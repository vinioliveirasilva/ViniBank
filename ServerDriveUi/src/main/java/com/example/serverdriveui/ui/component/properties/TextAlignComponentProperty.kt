package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

interface TextAlignComponentProperty {

    @Composable
    fun getTextAlign(): TextAlign
    fun setTextAlign(textAlign: TextAlignOption)
}