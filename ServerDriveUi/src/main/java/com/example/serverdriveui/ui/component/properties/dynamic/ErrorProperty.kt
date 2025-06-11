package com.example.serverdriveui.ui.component.properties.dynamic

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ErrorProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager
) : ErrorComponentProperty {
    private val propertyData = properties.find { it.name == "isError" }
    private val propertyId = propertyData?.id.orEmpty()
    private val propertyValue = propertyData?.value?.toBoolean() == true

    private lateinit var stateFlow: MutableStateFlow<Boolean>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Boolean>(propertyValue) },
            notNull = { stateManager.registerState<Boolean>(it, propertyValue) }
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