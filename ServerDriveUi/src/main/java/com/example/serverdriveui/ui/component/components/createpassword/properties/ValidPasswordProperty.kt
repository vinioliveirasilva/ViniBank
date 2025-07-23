package com.example.serverdriveui.ui.component.components.createpassword.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

data class ValidPasswordProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope
) : ValidPasswordComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "isPasswordValid",
        propertyValueTransformation = { it.toBoolean() },
        defaultPropertyValue = false.toString(),
        scope = scope
    ) {
    override fun isPasswordValid() = getValue()
    override fun setPasswordValid(isValid: Boolean) = setValue(isValid.toString())
}