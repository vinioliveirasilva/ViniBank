package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

data class EnabledProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : EnabledComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "enabled",
        propertyValueTransformation = { it.toBoolean() != false },
        defaultPropertyValue = true
    ) {

    override fun getEnabled() = getValue()

    override fun setEnabled(value: Boolean) = setValue(value)
}