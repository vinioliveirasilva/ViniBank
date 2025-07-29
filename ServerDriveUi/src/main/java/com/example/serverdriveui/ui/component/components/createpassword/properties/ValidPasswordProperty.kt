package com.example.serverdriveui.ui.component.components.createpassword.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asBoolean

data class ValidPasswordProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : ValidPasswordComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "isPasswordValid",
        transformToData = { it?.asBoolean() },
        defaultPropertyValue = false,
    ) {

    @Composable
    override fun isPasswordValid() = getValue()
    override fun setPasswordValid(isValid: Boolean) = setValue(isValid)
}

interface ValidPasswordComponentProperty {

    @Composable
    fun isPasswordValid() : Boolean
    fun setPasswordValid(isValid: Boolean)
}