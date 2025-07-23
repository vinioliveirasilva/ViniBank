package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class LabelProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : LabelComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "label",
        propertyValueTransformation = { it.orEmpty() },
        defaultPropertyValue = "",
        scope = scope
    ) {
    override fun getLabel() = getValue()
    override fun setLabel(value: String) = setValue(value)
}