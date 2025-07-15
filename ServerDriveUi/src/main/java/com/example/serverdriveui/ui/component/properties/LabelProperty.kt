package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

class LabelProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : LabelComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "label",
        propertyValueTransformation = { it },
        defaultPropertyValue = ""
    ) {
    override fun getLabel() = getValue()
    override fun setLabel(value: String) = setValue(value)
}