package com.example.serverdriveui.ui.component.components.createpassword.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ValidPasswordProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : ValidPasswordComponentProperty, BasePropertyData(properties, "isPasswordValid") {
    private val parsedValue = propertyValue.toBoolean()

    private lateinit var stateFlow: MutableStateFlow<Boolean>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow(parsedValue) },
            notNull = { stateManager.registerState<Boolean>(it, parsedValue) }
        )
    }

    override fun isPasswordValid(): StateFlow<Boolean> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Boolean>(it) ?: stateFlow }
        )
    }

    override fun setPasswordValid(isValid: Boolean) {
        propertyId.runWhen(
            isNull = { stateFlow.update { isValid } },
            notNull = { stateManager.updateState<Boolean>(it, isValid) }
        )
    }
}