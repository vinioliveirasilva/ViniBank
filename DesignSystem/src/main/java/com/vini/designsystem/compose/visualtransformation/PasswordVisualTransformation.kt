package com.vini.designsystem.compose.visualtransformation

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

fun getPasswordVisualTransformation(shouldShowPass: Boolean): VisualTransformation {
    return if (shouldShowPass) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }
}