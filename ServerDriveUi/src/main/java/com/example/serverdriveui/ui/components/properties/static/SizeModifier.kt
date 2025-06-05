package com.example.serverdriveui.ui.components.properties.static

import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vini.common.or

class SizeModifier(modifiers: Map<String, String>) : SizeComponentModifier {
    override val sizeModifier: Modifier = modifiers["size"]?.toIntOrNull()?.let { Modifier.size(it.dp) } or Modifier
}