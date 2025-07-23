package com.example.serverdriveui.ui.component.components.sdui.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface StageIdentifierComponent {
    fun getStageIdentifier(): StateFlow<String>
    fun setStageIdentifier(value: String)
}

class StageIdentifierProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : StageIdentifierComponent,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "stage",
        propertyValueTransformation = { it },
        defaultPropertyValue = "",
        scope = scope
    ) {
    override fun getStageIdentifier() = getValue()
    override fun setStageIdentifier(value: String) = setValue(value)
}