package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

class VerticalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : VerticalArrangementComponentProperty,
    BasePropertyData<Arrangement.Vertical>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalArrangement",
        propertyValueTransformation = { it.toVerticalArrangement() },
        defaultPropertyValue = Arrangement.Top
    ) {
    override fun getVerticalArrangement() = getValue()
    override fun setVerticalArrangement(value: Arrangement.Vertical) = setValue(value)
}

private fun String?.toVerticalArrangement() =
    when (this) {
        "Top" -> Arrangement.Top
        "Bottom" -> Arrangement.Bottom
        "Center" -> Arrangement.Center
        "SpaceAround" -> Arrangement.SpaceAround
        "SpaceBetween" -> Arrangement.SpaceBetween
        "SpaceEvenly" -> Arrangement.SpaceEvenly
        else -> null
    } ?: when (true) {
        this?.contains("SpacedBy") -> parseSpacedBy()
        else -> Arrangement.Top
    }

private fun String.parseSpacedBy() = (split("SpacedBy").lastOrNull()?.toInt() ?: 0).let {
    Arrangement.spacedBy(space = it.dp)
}