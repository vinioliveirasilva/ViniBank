package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import kotlinx.coroutines.CoroutineScope

data class VerticalFillTypeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : VerticalFillTypeComponentProperty,
    BasePropertyData<VerticalFillTypeOption>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalFillType",
        propertyValueTransformation = { it.toOption() },
        defaultPropertyValue = VerticalFillTypeOption.None.id,
        scope = scope
    ) {
    override val verticalFillTypeModifier: Modifier
        @Composable
        get() = getValue().asValue().modifier

    override fun getVerticalFillType() = getValue()
    override fun setVerticalFillType(value: VerticalFillTypeOption) = setValue(value.id)
}

enum class VerticalFillTypeOption(val id: String, val modifier: Modifier) {
    Max("Max", Modifier.Companion.fillMaxHeight()),
    Half("Half", Modifier.Companion.fillMaxHeight(.5f)),
    Quarter("Quarter", Modifier.Companion.fillMaxHeight(.25f)),
    Wrap("Wrap", Modifier.wrapContentHeight()),
    None("", Modifier)
}

private fun String?.toOption() = VerticalFillTypeOption.entries.firstOrNull { it.id == this } ?: VerticalFillTypeOption.None