package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

data class EnabledProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : EnabledComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "enabled",
        propertyValueTransformation = { it.toBoolean() },
        defaultPropertyValue = true.toString(),
        scope = scope
    ) {

    override fun getEnabled() = getValue()

    override fun setEnabled(value: Boolean) = setValue(value.toString())
}