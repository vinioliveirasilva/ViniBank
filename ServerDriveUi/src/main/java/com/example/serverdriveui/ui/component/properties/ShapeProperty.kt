package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString

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

enum class ShapeOptions(val id: String, val shape: Shape) {
    None("None", RoundedCornerShape(0.dp)),
    Small("Small", RoundedCornerShape(4.dp)),
    Medium("Medium", RoundedCornerShape(8.dp)),
    Large("Large", RoundedCornerShape(16.dp)),
    Circle("Circle", CircleShape),
}

private fun String?.toOption(): ShapeOptions =
    ShapeOptions.entries.firstOrNull { it.id == this } ?: ShapeOptions.None
