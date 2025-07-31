package com.example.serverdriveui.ui.component.components.sdui.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import kotlinx.coroutines.flow.StateFlow

interface FromScreenIdentifierComponent {
    fun getFromScreenIdentifier(): StateFlow<String>
}

class FromScreenIdentifierComponentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : FromScreenIdentifierComponent, BasePropertyData<String>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "currentScreen",
    transformToData = { it?.asString() },
    defaultPropertyValue = "",
) {
    override fun getFromScreenIdentifier() = getValueAsState()
}