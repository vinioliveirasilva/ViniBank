package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.text.KeyboardOptions
import kotlinx.coroutines.flow.StateFlow

interface KeyboardOptionsComponentProperty {
    fun getKeyboardOptions(): StateFlow<KeyboardOptions>
    fun setKeyboardOptions(keyboardOptions: KeyboardOptionsOption)
}