package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

data class ErrorMessageProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : ErrorMessageComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "errorMessage",
        propertyValueTransformation = { it },
        defaultPropertyValue = ""
    ) {
    override fun getErrorMessage() = getValue()
    override fun setErrorMessage(message: String) = setValue(message)
}