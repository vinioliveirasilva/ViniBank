package com.vini.designsystem.compose.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

fun passwordTrailingIcon(
    showPassword: Boolean,
    onVisibilityChange: (Boolean) -> Unit
): @Composable () -> Unit {
    return {
        if (showPassword) {
            IconButton(
                onClick = { onVisibilityChange(false) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "hide_password"
                )
            }
        } else {
            IconButton(
                onClick = { onVisibilityChange(true) }
            ) {
                Icon(
                    imageVector = Icons.Filled.VisibilityOff,
                    contentDescription = "hide_password"
                )
            }
        }
    }
}