package com.example.serverdriveui.ui.component.components.navigationbar.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager

class NavigationDestinationIndexProperty(
    properties: Map<String, PropertyModel>,
    stateManager: ComponentStateManager
) : NavigationDestinationIndexComponent, BasePropertyData<Int>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "index",
    propertyValueTransformation = { value -> value.toIntOrNull() },
    defaultPropertyValue = 0
) {
    override fun getIndex() = getValue()
}