package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.HorizontalFillType.Companion.toHorizontalFillType
import com.example.serverdriveui.ui.component.properties.HorizontalFillType.Wrap
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue

data class HorizontalFillTypeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : HorizontalFillTypeComponentProperty,
    BasePropertyData<HorizontalFillType>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalFillType",
        propertyValueTransformation = { it.toHorizontalFillType() },
        defaultPropertyValue = Wrap
    ) {

    override val horizontalFillTypeModifier: Modifier
        @Composable
        get() = getValue().asValue().modifier

    override fun getHorizontalFillType() = getValue()
    override fun setHorizontalFillType(value: HorizontalFillType) = setValue(value)
}