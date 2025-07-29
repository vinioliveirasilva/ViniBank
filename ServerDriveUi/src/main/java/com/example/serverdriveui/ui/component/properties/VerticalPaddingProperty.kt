package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asInt

data class VerticalPaddingProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VerticalPaddingComponentProperty,
    BasePropertyData<Int>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "paddingVertical",
        transformToData = { it?.asInt() },
        defaultPropertyValue = 0,
    ) {
    override val verticalPaddingModifier: Modifier
        @Composable
        get() = Modifier.padding(vertical = getValue().dp)
}

interface VerticalPaddingComponentProperty {
    @get:Composable
    val verticalPaddingModifier: Modifier
}