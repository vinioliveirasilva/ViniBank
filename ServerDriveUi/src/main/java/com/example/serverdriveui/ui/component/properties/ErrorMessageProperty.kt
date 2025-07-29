package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString

data class ErrorMessageProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : ErrorMessageComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "errorMessage",
        defaultPropertyValue = "",
        transformToData = { it?.asString() }
    ) {

    @Composable
    override fun getErrorMessage() = getValue()
    override fun setErrorMessage(message: String) = setValue(message)
}

interface ErrorMessageComponentProperty {

    @Composable
    fun getErrorMessage() : String
    fun setErrorMessage(message: String)
}