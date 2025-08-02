package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.options.HorizontalArrangementOption

class HorizontalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : HorizontalArrangementComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalArrangement",
        defaultPropertyValue = HorizontalArrangementOption.Start.name,
        transformToData = { it?.asString() }
    ) {

    @Composable
    override fun getHorizontalArrangement() = HorizontalArrangementOption.valueOf(getValue()).toArrangement()
    override fun setHorizontalArrangement(value: HorizontalArrangementOption) = setValue(value.name)

    private fun HorizontalArrangementOption.toArrangement() = when(this) {
        HorizontalArrangementOption.Start -> Arrangement.Start
        HorizontalArrangementOption.End -> Arrangement.End
        HorizontalArrangementOption.Center -> Arrangement.Center
        HorizontalArrangementOption.SpaceBetween -> Arrangement.SpaceBetween
        HorizontalArrangementOption.SpaceAround -> Arrangement.SpaceAround
    }
}

interface HorizontalArrangementComponentProperty {

    @Composable
    fun getHorizontalArrangement(): Arrangement.Horizontal
    fun setHorizontalArrangement(value: HorizontalArrangementOption)
}