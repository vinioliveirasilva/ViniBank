package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.options.ShapeOptions

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
    override fun getShape() = ShapeOptions.valueOf(getValue()).toShape()

    override fun setShape(shape: ShapeOptions) = setValue(shape.name)


    private fun ShapeOptions.toShape() = when(this) {
        ShapeOptions.None -> RoundedCornerShape(0.dp)
        ShapeOptions.Small -> RoundedCornerShape(4.dp)
        ShapeOptions.Medium -> RoundedCornerShape(8.dp)
        ShapeOptions.Large -> RoundedCornerShape(16.dp)
        ShapeOptions.Circle -> CircleShape
    }
}