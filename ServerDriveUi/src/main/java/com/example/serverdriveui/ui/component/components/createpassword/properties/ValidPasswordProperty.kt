package com.example.serverdriveui.ui.component.components.createpassword.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager

data class ValidPasswordProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : ValidPasswordComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "isPasswordValid",
        propertyValueTransformation = { it.toBoolean() },
        defaultPropertyValue = false
    ) {
    override fun isPasswordValid() = getValue()
    override fun setPasswordValid(isValid: Boolean) = setValue(isValid)
}