package com.example.serverdriveui.ui.component.properties.static

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.ui.Modifier
import com.vini.common.or

class WeightModifier(modifiers: Map<String, String>) : WeightComponentModifier {
    override val weightModifier: ColumnScope.()  -> Modifier = {
        modifiers["weight"]?.toFloat()?.let { Modifier.weight(it) } or Modifier
    }
}