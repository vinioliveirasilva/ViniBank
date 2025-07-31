package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asBoolean

data class ErrorProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : ErrorComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "isError",
        defaultPropertyValue = false,
        transformToData = { it?.asBoolean() }
    ) {

    @Composable
    override fun getIsError() = getValue()
    override fun setIsError(value: Boolean) = setValue(value)
}

interface ErrorComponentProperty {

    @Composable
    fun getIsError() : Boolean
    fun setIsError(value: Boolean)
}