package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

open class BasePropertyData<T>(
    private val stateManager: ComponentStateManager,
    properties: Map<String, PropertyModel>,
    propertyName: String,
    propertyValueTransformation: (String) -> T?,
    defaultPropertyValue: T
) {
    private val propertyData = properties[propertyName]
    private val propertyId = propertyData?.id.orEmpty()
    private val propertyValue = propertyData?.value?.let(propertyValueTransformation) ?: defaultPropertyValue
    private lateinit var stateFlow: MutableStateFlow<T>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow(propertyValue) },
            notNull = { stateManager.registerState<T>(it, propertyValue) }
        )
    }

    fun getValue() : StateFlow<T> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState(it) ?: stateFlow }
        )
    }

    fun setValue(value: T) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState(it, value) }
        )
    }
}