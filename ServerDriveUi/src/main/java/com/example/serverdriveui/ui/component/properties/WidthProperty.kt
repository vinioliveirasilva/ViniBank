package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import kotlinx.coroutines.CoroutineScope

class WidthProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : WidthComponentProperty,
    BasePropertyData<Int?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "width",
        propertyValueTransformation = { it.toIntOrNull() },
        defaultPropertyValue = "",
        scope = scope
    ) {

    override fun getWidth() = getValue()
    override fun setWidth(value: Int) = setValue(value.toString())
    override val widthModifier: Modifier
        @Composable
        get() = getValue().asValue()?.let { Modifier.width(it.dp) } ?: Modifier

}