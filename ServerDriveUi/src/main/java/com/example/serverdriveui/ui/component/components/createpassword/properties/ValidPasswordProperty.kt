package com.example.serverdriveui.ui.component.components.createpassword.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ValidPasswordProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager
) : ValidPasswordComponentProperty {
    private val propertyData = properties.find { it.name == "isPasswordValid" }
    private val propertyId = propertyData?.id.orEmpty()
    private val propertyValue = propertyData?.value.toBoolean()

    private lateinit var stateFlow: MutableStateFlow<Boolean>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow(propertyValue) },
            notNull = { stateManager.registerState<Boolean>(it, propertyValue) }
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