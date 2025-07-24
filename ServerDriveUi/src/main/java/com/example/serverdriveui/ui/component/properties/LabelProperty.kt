package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString

class LabelProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : LabelComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "label",
        defaultPropertyValue = "",
        transformToData = { it?.asString() }
    ) {

    @Composable
    override fun getLabel() = getValue()
    override fun setLabel(value: String) = setValue(value)
}