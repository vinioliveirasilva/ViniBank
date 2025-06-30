package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.VerticalFillType.Companion.toVerticalFillType
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue

data class VerticalFillTypeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : VerticalFillTypeComponentProperty,
    BasePropertyData<VerticalFillType>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalFillType",
        propertyValueTransformation = { it.toVerticalFillType() },
        defaultPropertyValue = VerticalFillType.None
    ) {
    override val verticalFillTypeModifier: Modifier
        @Composable
        get() = getValue().asValue().modifier

    override fun getVerticalFillType() = getValue()
    override fun setVerticalFillType(value: VerticalFillType) = setValue(value)
}