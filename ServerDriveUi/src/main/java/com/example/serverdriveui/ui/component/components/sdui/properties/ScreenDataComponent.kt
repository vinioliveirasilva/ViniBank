package com.example.serverdriveui.ui.component.components.sdui.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

interface ScreenDataComponent {
    fun getScreenData(): StateFlow<JsonObject>
    fun setScreenData(value: JsonObject)
}

class ScreenDataProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : ScreenDataComponent, BasePropertyData<JsonObject>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "screenData",
    propertyValueTransformation = { Json.decodeFromString(it) },
    defaultPropertyValue = Json.encodeToString(JsonObject(emptyMap())),
    scope = scope
) {
    override fun getScreenData() = getValue()
    override fun setScreenData(value: JsonObject) = setValue(Json.encodeToString(value))
}