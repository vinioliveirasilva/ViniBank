package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import kotlinx.coroutines.CoroutineScope

class HeightProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : HeightComponentProperty,
    BasePropertyData<Int?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "height",
        propertyValueTransformation = { it.toIntOrNull() },
        defaultPropertyValue = "",
        scope = scope
    ) {

    override fun getHeight()= getValue()
    override fun setHeight(value: Int) = setValue(value.toString())
    override val heightModifier: Modifier
        @Composable
        get() = getValue().asValue()?.let { Modifier.height(it.dp) } ?: Modifier

}