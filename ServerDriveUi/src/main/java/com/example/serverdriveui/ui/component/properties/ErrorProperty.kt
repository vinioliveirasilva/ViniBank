package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

data class ErrorProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : ErrorComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "isError",
        propertyValueTransformation = { it.toBoolean() },
        defaultPropertyValue = false.toString(),
        scope = scope
    ) {
    override fun getIsError() = getValue()
    override fun setIsError(value: Boolean) = setValue(value.toString())
}