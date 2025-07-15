package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

class VerticalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : VerticalAlignmentComponentProperty,
    BasePropertyData<Alignment.Vertical>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalAlignment",
        propertyValueTransformation = { it.toVerticalAlignment() },
        defaultPropertyValue = Alignment.Top
    ) {
    override fun getVerticalAlignment() = getValue()
    override fun setVerticalAlignment(value: Alignment.Vertical) = setValue(value)
}

private fun String?.toVerticalAlignment(): Alignment.Vertical = when (this) {
    "Center" -> Alignment.Companion.CenterVertically
    "Bottom" -> Alignment.Companion.Bottom
    "Top" -> Alignment.Companion.Top
    else -> Alignment.Companion.Top
}