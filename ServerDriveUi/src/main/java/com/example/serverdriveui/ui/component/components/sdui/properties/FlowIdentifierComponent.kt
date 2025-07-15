package com.example.serverdriveui.ui.component.components.sdui.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.flow.StateFlow

interface FlowIdentifierComponent {
    fun getFlowIdentifier(): StateFlow<String>
    fun setFlowIdentifier(value: String)
}

class FlowIdentifierProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : FlowIdentifierComponent, BasePropertyData<String>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "flow",
    propertyValueTransformation = { it },
    defaultPropertyValue = ""
) {
    override fun getFlowIdentifier() = getValue()
    override fun setFlowIdentifier(value: String) = setValue(value)
}