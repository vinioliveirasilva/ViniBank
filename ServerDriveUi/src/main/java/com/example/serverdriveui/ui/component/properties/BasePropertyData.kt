package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

open class BasePropertyData<T>(
    private val stateManager: ComponentStateManager,
    private val properties: Map<String, PropertyModel>,
    private val propertyName: String,
    private val propertyValueTransformation: (String) -> T,
    private val defaultPropertyValue: String,
    private val scope: CoroutineScope,
) {
    private val propertyData = properties[propertyName]
    private val propertyId = propertyData?.id.orEmpty()
    private val propertyValue = propertyData?.value ?: defaultPropertyValue
    private lateinit var stateFlow: MutableStateFlow<String?>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow(propertyValue) },
            notNull = { stateManager.registerState<String?>(it, propertyValue) }
        )
    }

    fun getValue(): StateFlow<T> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState(it) ?: stateFlow }
        )
            .filterNotNull()
            .map { propertyValueTransformation(it) }
            .stateIn(
                scope = scope,
                started = SharingStarted.Eagerly,
                initialValue = propertyValueTransformation(propertyValue)
            )
    }

    fun setValue(value: String) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState(it, value) }
        )
    }
}