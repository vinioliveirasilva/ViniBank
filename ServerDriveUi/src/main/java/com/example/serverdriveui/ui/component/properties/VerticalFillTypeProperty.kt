package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.VerticalFillTypeOption

data class VerticalFillTypeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VerticalFillTypeComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalFillType",
        transformToData = { it?.asString() },
        defaultPropertyValue = VerticalFillTypeOption.None.name,
    ) {
    override val verticalFillTypeModifier: Modifier
        @Composable
        get() = VerticalFillTypeOption.valueOf(getValue()).modifier
}

interface VerticalFillTypeComponentProperty {
    @get:Composable
    val verticalFillTypeModifier: Modifier
}