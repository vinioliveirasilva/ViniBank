package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

data class TextProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : TextComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "text",
        propertyValueTransformation = { it },
        defaultPropertyValue = ""
    ) {
    override fun getText() = getValue()
    override fun setText(text: String) = setValue(text)
}