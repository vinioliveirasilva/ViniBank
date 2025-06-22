package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class KeyboardOptionsProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : KeyboardOptionsComponentProperty, BasePropertyData(properties, "textFormatter") {

    private val parsedValue = propertyValue.toKeyboardOption()
    private lateinit var stateFlow: MutableStateFlow<KeyboardOptions>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<KeyboardOptions>(parsedValue) },
            notNull = { stateManager.registerState<KeyboardOptions>(it, parsedValue) }
        )
    }

    override fun getKeyboardOptions(): StateFlow<KeyboardOptions> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<KeyboardOptions>(it) ?: stateFlow }
        )
    }

    override fun setKeyboardOptions(keyboardOptions: KeyboardOptions) {
        propertyId.runWhen(
            isNull = { stateFlow.update { keyboardOptions } },
            notNull = { stateManager.updateState<KeyboardOptions>(it, keyboardOptions) }
        )
    }

    private fun String?.toKeyboardOption() = when (this) {
        "Documento.CPF" -> KeyboardOptions(keyboardType = KeyboardType.Companion.Number)
        "Telefone" -> KeyboardOptions(keyboardType = KeyboardType.Companion.Phone)
        else -> KeyboardOptions.Companion.Default
    }
}