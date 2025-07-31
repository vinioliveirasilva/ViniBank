package com.vini.designsystemsdui.property

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

enum class KeyboardOptionsOption(val keyboardOptions: KeyboardOptions) {
    Default(KeyboardOptions.Companion.Default),
    Number(KeyboardOptions(keyboardType = KeyboardType.Companion.Number)),
    Phone(KeyboardOptions(keyboardType = KeyboardType.Companion.Phone)),
    Password(KeyboardOptions(keyboardType = KeyboardType.Companion.Password)),
}