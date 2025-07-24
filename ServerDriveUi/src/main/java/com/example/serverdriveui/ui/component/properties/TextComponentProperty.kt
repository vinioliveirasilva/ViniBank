package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable

interface TextComponentProperty {

    @Composable
    fun getText() : String
    fun setText(text: String)
}