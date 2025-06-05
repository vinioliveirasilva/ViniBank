package com.example.serverdriveui.ui.components.properties.static

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


data class PaddingModifier(
    private val modifiers: Map<String, String>
) : PaddingComponentModifier {
    override val paddingModifier = Modifier.padding(
        horizontal = (modifiers["paddingHorizontal"]?.toIntOrNull() ?: 0).dp,
        vertical = (modifiers["paddingVertical"]?.toIntOrNull() ?: 0).dp
    )
}