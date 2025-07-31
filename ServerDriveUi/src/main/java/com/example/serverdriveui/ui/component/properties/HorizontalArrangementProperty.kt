package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.HorizontalArrangementOptions

class HorizontalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : HorizontalArrangementComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalArrangement",
        defaultPropertyValue = HorizontalArrangementOptions.Start.name,
        transformToData = { it?.asString() }
    ) {

    @Composable
    override fun getHorizontalArrangement() = HorizontalArrangementOptions.valueOf(getValue()).arrangement
    override fun setHorizontalArrangement(value: HorizontalArrangementOptions) = setValue(value.name)
}

interface HorizontalArrangementComponentProperty {

    @Composable
    fun getHorizontalArrangement(): Arrangement.Horizontal
    fun setHorizontalArrangement(value: HorizontalArrangementOptions)
}