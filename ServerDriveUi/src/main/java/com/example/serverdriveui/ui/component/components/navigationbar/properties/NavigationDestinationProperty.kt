package com.example.serverdriveui.ui.component.components.navigationbar.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.flow.StateFlow

class NavigationDestinationProperty(
    properties: Map<String, PropertyModel>,
    stateManager: ComponentStateManager
) : NavigationDestinationComponent, BasePropertyData<Int>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "selectedDestination",
    propertyValueTransformation = { value -> value.toIntOrNull() },
    defaultPropertyValue = 0
) {
    override fun getSelectedDestination(): StateFlow<Int> = getValue()
    override fun setSelectedDestination(index: Int) = setValue(index)
}