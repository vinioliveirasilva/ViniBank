package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

class ContentAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : ContentAlignmentComponentProperty,
    BasePropertyData<Alignment>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "contentAlignment",
        propertyValueTransformation = { it.toAlignment() },
        defaultPropertyValue = Alignment.TopStart
    ) {
    override fun getContentAlignment() = getValue()
    override fun setContentAlignment(value: Alignment) = setValue(value)
}

private fun String?.toAlignment(): Alignment = when (this) {
    "TopStart" -> Alignment.TopStart
    "TopCenter" -> Alignment.TopCenter
    "TopEnd" -> Alignment.TopEnd
    "CenterStart" -> Alignment.CenterStart
    "Center" -> Alignment.Center
    "CenterEnd" -> Alignment.CenterEnd
    "BottomStart" -> Alignment.BottomStart
    "BottomCenter" -> Alignment.BottomCenter
    "BottomEnd" -> Alignment.BottomEnd
    else -> Alignment.TopStart
}