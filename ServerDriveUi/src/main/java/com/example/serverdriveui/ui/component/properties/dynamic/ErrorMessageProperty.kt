package com.example.serverdriveui.ui.component.properties.dynamic

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ErrorMessageProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager
) : ErrorMessageComponentProperty {
    private val propertyData = properties.find { it.name == "errorMessage" }
    private val propertyId = propertyData?.id.orEmpty()
    private val propertyValue = propertyData?.value.orEmpty()

    private lateinit var stateFlow: MutableStateFlow<String>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<String>(propertyValue) },
            notNull = { stateManager.registerState<String>(it, propertyValue) }
        )
    }

    override fun getErrorMessage(): StateFlow<String> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<String>(it) ?: stateFlow }
        )
    }

    override fun setErrorMessage(message: String) {
        propertyId.runWhen(
            isNull = { stateFlow.update { message } },
            notNull = { stateManager.updateState<String>(it, message) }
        )
    }
}