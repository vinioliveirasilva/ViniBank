package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

class HorizontalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : HorizontalAlignmentComponentProperty,
    BasePropertyData<Alignment.Horizontal>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalAlignment",
        propertyValueTransformation = { it.toHorizontalAlignment() },
        defaultPropertyValue = Alignment.Start
    ) {
    override fun getHorizontalAlignment() = getValue()
    override fun setHorizontalAlignment(value: Alignment.Horizontal) = setValue(value)
}

private fun String?.toHorizontalAlignment(): Alignment.Horizontal = when (this) {
    "Center" -> Alignment.CenterHorizontally
    "Start" -> Alignment.Start
    "End" -> Alignment.End
    else -> Alignment.Start
}