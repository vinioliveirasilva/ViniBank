package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.VerticalArrangementOption
import com.vini.designsystemsdui.property.toVerticalArrangement

class VerticalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VerticalArrangementComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalArrangement",
        transformToData = { it?.asString() },
        defaultPropertyValue = VerticalArrangementOption.Top.name,
    ) {

    @Composable
    override fun getVerticalArrangement() =
        getValue().toVerticalArrangement().verticalArrangement

    override fun setVerticalArrangement(value: VerticalArrangementOption) = setValue(value.name)
}

interface VerticalArrangementComponentProperty {

    @Composable
    fun getVerticalArrangement(): Arrangement.Vertical
    fun setVerticalArrangement(value: VerticalArrangementOption)
}