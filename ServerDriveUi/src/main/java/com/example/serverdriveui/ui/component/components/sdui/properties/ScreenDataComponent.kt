package com.example.serverdriveui.ui.component.components.sdui.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject

interface ScreenDataComponent {
    fun getScreenData(): StateFlow<JsonObject>
    fun setScreenData(value: JsonObject)
}

class ScreenDataProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : ScreenDataComponent, BasePropertyData<JsonObject>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "screenData",
    transformToData = { it?.jsonObject },
    defaultPropertyValue = JsonObject(emptyMap()),
) {
    override fun getScreenData() = getValueAsState()
    override fun setScreenData(value: JsonObject) = setValue(value)
}