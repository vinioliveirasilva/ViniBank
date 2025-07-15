package com.example.serverdriveui.ui.component.components.sdui.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.flow.StateFlow

interface ScreenDataComponent {
    fun getScreenData(): StateFlow<String>
    fun setScreenData(value: String)
}

class ScreenDataProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : ScreenDataComponent, BasePropertyData<String>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "screenData",
    propertyValueTransformation = { it },
    defaultPropertyValue = ""
) {
    override fun getScreenData() = getValue()
    override fun setScreenData(value: String) = setValue(value)
}