package com.example.serverdriveui.ui.component.components.sdui.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import kotlinx.coroutines.flow.StateFlow

interface StageIdentifierComponent {
    fun getStageIdentifier(): StateFlow<String>
    fun setStageIdentifier(value: String)
}

class StageIdentifierProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,

) : StageIdentifierComponent,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "stage",
        transformToData = { it?.asString() },
        defaultPropertyValue = "",
    ) {
    override fun getStageIdentifier() = getValueAsState()
    override fun setStageIdentifier(value: String) = setValue(value)
}