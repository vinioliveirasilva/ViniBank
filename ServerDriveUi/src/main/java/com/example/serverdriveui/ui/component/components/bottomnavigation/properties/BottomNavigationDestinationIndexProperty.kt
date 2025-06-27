package com.example.serverdriveui.ui.component.components.bottomnavigation.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager

class BottomNavigationDestinationIndexProperty(
    properties: Map<String, PropertyModel>,
    stateManager: ComponentStateManager
) : BottomNavigationDestinationIndexComponent, BasePropertyData<Int>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "index",
    propertyValueTransformation = { value -> value.toIntOrNull() },
    defaultPropertyValue = 0
) {
    override fun getIndex() = getValue()
}