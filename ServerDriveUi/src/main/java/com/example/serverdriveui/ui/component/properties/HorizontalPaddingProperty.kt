package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import kotlinx.coroutines.CoroutineScope

data class HorizontalPaddingProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : HorizontalPaddingComponentProperty,
    BasePropertyData<Int>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "paddingHorizontal",
        propertyValueTransformation = { it?.toIntOrNull() ?: 0 },
        defaultPropertyValue = "0",
        scope = scope
    ) {
    override val horizontalPaddingModifier: Modifier
        @Composable
        get() = Modifier.padding(horizontal = getValue().asValue().dp)

    override fun getHorizontalPadding() = getValue()
    override fun setHorizontalPadding(padding: Int) = setValue(padding.toString())
}