package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.ShapeOptions

interface ShapeComponentProperty {
    @Composable
    fun getShape(): Shape
    fun setShape(shape: ShapeOptions)
}

class ShapeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : ShapeComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "shape",
        transformToData = { it?.asString() },
        defaultPropertyValue = ShapeOptions.Circle.name,
    ) {
    @Composable
    override fun getShape() = ShapeOptions.valueOf(getValue()).shape

    override fun setShape(shape: ShapeOptions) = setValue(shape.name)
}