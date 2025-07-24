package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable

interface KeyboardOptionsComponentProperty {
    @Composable
    fun getKeyboardOptions(): KeyboardOptions
    fun setKeyboardOptions(keyboardOptions: KeyboardOptionsOption)
}