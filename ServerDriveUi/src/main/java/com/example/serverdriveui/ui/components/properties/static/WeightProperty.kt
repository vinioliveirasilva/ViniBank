package com.example.serverdriveui.ui.components.properties.static

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.ui.Modifier
import com.vini.common.or

class WeightProperty(modifiers: Map<String, String>) : WeightComponentProperty {
    override val weightModifier: ColumnScope.()  -> Modifier = {
        modifiers["weight"]?.toFloat()?.let { Modifier.weight(it) } or Modifier
    }
}