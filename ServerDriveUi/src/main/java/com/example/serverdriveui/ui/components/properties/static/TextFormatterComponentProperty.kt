package com.example.serverdriveui.ui.components.properties.static

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.VisualTransformation

interface TextFormatterComponentProperty {
    val visualTransformation: VisualTransformation
    val keyboardOptions: KeyboardOptions
}