package com.example.serverdriveui.ui.component.components.pager.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class ContentPaddingProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : ContentPaddingComponentProperty,
    BasePropertyData<Int>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "contentPadding",
        propertyValueTransformation = { it.toInt() },
        defaultPropertyValue = 0.toString(),
        scope = scope
    ) {
    override fun getContentPadding() = getValue()

    override fun setContentPadding(value: Int) = setValue(value.toString())
}