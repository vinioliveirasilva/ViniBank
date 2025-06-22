package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ErrorMessageProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : ErrorMessageComponentProperty, BasePropertyData(properties, "errorMessage") {
    private val propertyParsed = propertyValue.orEmpty()

    private lateinit var stateFlow: MutableStateFlow<String>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<String>(propertyParsed) },
            notNull = { stateManager.registerState<String>(it, propertyParsed) }
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