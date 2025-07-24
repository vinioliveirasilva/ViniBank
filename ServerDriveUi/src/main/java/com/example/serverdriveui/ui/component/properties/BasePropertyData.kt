package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.JsonElement

open class BasePropertyData<T>(
    private val stateManager: ComponentStateManager,
    private val properties: Map<String, PropertyModel>,
    private val propertyName: String,
    private val transformToData: (JsonElement?) -> T?,
    private val defaultPropertyValue: T,
) {
    private val propertyData = properties[propertyName]
    private val propertyId = propertyData?.id
    private val propertyValue = transformToData(propertyData?.value) ?: defaultPropertyValue
    private lateinit var stateFlow: MutableStateFlow<T>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow(propertyValue) },
            notNull = { stateManager.registerState<T>(it, propertyValue) }
        )
    }

    @Composable
    fun getValue(): T {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState(it) ?: stateFlow }
        ).asValue()
    }

    fun getValueAsState(): StateFlow<T> {
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