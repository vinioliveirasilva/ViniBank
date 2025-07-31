package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asBoolean

interface EnabledComponentProperty {
    @Composable
    fun getEnabled() : Boolean
    fun setEnabled(value: Boolean)
}

data class EnabledProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : EnabledComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "enabled",
        defaultPropertyValue = true,
        transformToData = { it?.asBoolean() }
    ) {

    @Composable
    override fun getEnabled() = getValue()

    override fun setEnabled(value: Boolean) = setValue(value)
}