package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

class HorizontalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : HorizontalArrangementComponentProperty,
    BasePropertyData<Arrangement.Horizontal>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalArrangement",
        propertyValueTransformation = { it.toHorizontalArrangement() },
        defaultPropertyValue = Arrangement.Start
    ) {
    override fun getHorizontalArrangement() = getValue()
    override fun setHorizontalArrangement(value: Arrangement.Horizontal) = setValue(value)
}

private fun String?.toHorizontalArrangement() = when (this) {
    "Start" -> Arrangement.Start
    "End" -> Arrangement.End
    "Center" -> Arrangement.Center
    "SpaceBetween" -> Arrangement.SpaceBetween
    "SpaceAround" -> Arrangement.SpaceAround
    else -> Arrangement.Start
}