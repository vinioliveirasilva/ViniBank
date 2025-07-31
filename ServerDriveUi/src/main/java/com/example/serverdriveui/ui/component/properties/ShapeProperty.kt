package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.PropertyOptions.ShapeOptions

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
        defaultPropertyValue = ShapeOptions.Circle.id,
    ) {
    @Composable
    override fun getShape() = getValue().toOption().shape

    override fun setShape(shape: ShapeOptions) = setValue(shape.id)
}

private fun String?.toOption(): ShapeOptions =
    ShapeOptions.entries.firstOrNull { it.id == this } ?: ShapeOptions.None
