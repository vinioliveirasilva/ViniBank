package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString

data class HorizontalFillTypeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : HorizontalFillTypeComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalFillType",
        defaultPropertyValue = HorizontalFillTypeOption.None.id,
        transformToData = { it?.asString() }
    ) {

    override val horizontalFillTypeModifier: Modifier
        @Composable
        get() = getValue().toOptions().modifier
}

enum class HorizontalFillTypeOption(val id: String, val modifier: Modifier) {
    Max("Max", Modifier.fillMaxWidth()),
    Half("Half", Modifier.fillMaxWidth(.5f)),
    Quarter("Quarter", Modifier.fillMaxWidth(.25f)),
    Wrap("Wrap", Modifier.wrapContentWidth()),
    None("", Modifier),
}

private fun String?.toOptions(): HorizontalFillTypeOption =
    HorizontalFillTypeOption.entries.firstOrNull { it.id == this } ?: HorizontalFillTypeOption.None

interface HorizontalFillTypeComponentProperty {
    @get:Composable
    val horizontalFillTypeModifier: Modifier
}