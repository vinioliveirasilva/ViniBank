package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

data class ErrorMessageProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : ErrorMessageComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "errorMessage",
        propertyValueTransformation = { it },
        defaultPropertyValue = "",
        scope = scope
    ) {
    override fun getErrorMessage() = getValue()
    override fun setErrorMessage(message: String) = setValue(message)
}