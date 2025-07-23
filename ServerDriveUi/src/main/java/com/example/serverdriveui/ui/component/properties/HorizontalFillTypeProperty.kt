package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import kotlinx.coroutines.CoroutineScope

data class HorizontalFillTypeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : HorizontalFillTypeComponentProperty,
    BasePropertyData<HorizontalFillType>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalFillType",
        propertyValueTransformation = { it.toHorizontalFillType() },
        defaultPropertyValue = HorizontalFillType.None.id,
        scope = scope
    ) {

    override val horizontalFillTypeModifier: Modifier
        @Composable
        get() = getValue().asValue().modifier

    override fun getHorizontalFillType() = getValue()
    override fun setHorizontalFillType(value: HorizontalFillType) = setValue(value.id)
}

enum class HorizontalFillType(val id: String, val modifier: Modifier) {
    Max("Max",  Modifier.fillMaxWidth()),
    Half("Half",  Modifier.fillMaxWidth(.5f)),
    Quarter("Quarter",  Modifier.fillMaxWidth(.25f)),
    Wrap("Wrap",  Modifier.wrapContentWidth()),
    None("",  Modifier),
}

private fun String?.toHorizontalFillType(): HorizontalFillType = HorizontalFillType.entries.firstOrNull { it.id == this } ?: HorizontalFillType.None