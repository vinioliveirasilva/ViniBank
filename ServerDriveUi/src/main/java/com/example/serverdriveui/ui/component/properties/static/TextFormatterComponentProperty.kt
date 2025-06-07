package com.example.serverdriveui.ui.component.properties.static

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.VisualTransformation

interface TextFormatterComponentProperty {
    val visualTransformation: VisualTransformation
    val keyboardOptions: KeyboardOptions
}