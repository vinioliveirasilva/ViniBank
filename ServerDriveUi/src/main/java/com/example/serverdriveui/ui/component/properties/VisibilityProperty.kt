package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class VisibilityProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : VisibilityComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "isVisible",
        propertyValueTransformation = { it.toBoolean() },
        defaultPropertyValue = true.toString(),
        scope = scope
    ) {

    override fun getIsVisible() = getValue()
    override fun setIsVisible(isVisible: Boolean) = setValue(isVisible.toString())
}