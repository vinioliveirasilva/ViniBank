package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
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

private fun String?.toVerticalArrangement() = when (this) {
    "Top" -> Arrangement.Top
    "Bottom" -> Arrangement.Bottom
    "Center" -> Arrangement.Center
    "SpaceAround" -> Arrangement.SpaceAround
    "SpaceBetween" -> Arrangement.SpaceBetween
    "SpaceEvenly" -> Arrangement.SpaceEvenly
    else -> Arrangement.Top
}