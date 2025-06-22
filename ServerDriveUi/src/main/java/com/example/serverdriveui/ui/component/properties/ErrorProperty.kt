package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ErrorProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : ErrorComponentProperty, BasePropertyData(properties, "isError") {
    private val propertyParsed = propertyValue.toBoolean() == true

    private lateinit var stateFlow: MutableStateFlow<Boolean>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Boolean>(propertyParsed) },
            notNull = { stateManager.registerState<Boolean>(it, propertyParsed) }
        )
    }

    override fun getIsError(): StateFlow<Boolean> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Boolean>(it) ?: stateFlow }
        )
    }

    override fun setIsError(value: Boolean) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<Boolean>(it, value) }
        )
    }
}