package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.PropertyOptions.VerticalAlignmentOption

class VerticalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VerticalAlignmentComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalAlignment",
        transformToData = { it?.asString() },
        defaultPropertyValue = VerticalAlignmentOption.Top.id,
    ) {

    @Composable
    override fun getVerticalAlignment() = getValue().toOption().verticalAlignment

    override fun setVerticalAlignment(value: VerticalAlignmentOption) = setValue(value.id)
}

private fun String?.toOption() =
    VerticalAlignmentOption.entries.firstOrNull { it.id == this } ?: VerticalAlignmentOption.Top

interface VerticalAlignmentComponentProperty {
    @Composable
    fun getVerticalAlignment(): Alignment.Vertical
    fun setVerticalAlignment(value: VerticalAlignmentOption)
}