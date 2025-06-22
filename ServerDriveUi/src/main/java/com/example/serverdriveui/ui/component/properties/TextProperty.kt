package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class TextProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : TextComponentProperty, BasePropertyData(properties, "text") {

    private lateinit var stateFlow: MutableStateFlow<String>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow(propertyValue.orEmpty()) },
            notNull = { stateManager.registerState<String>(it, propertyValue.orEmpty()) }
        )
    }

    override fun getText(): StateFlow<String> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<String>(it) ?: stateFlow }
        )
    }

    override fun setText(text: String) {
        propertyId.runWhen(
            isNull = { stateFlow.update { text } },
            notNull = { stateManager.updateState<String>(it, text) }
        )
    }
}