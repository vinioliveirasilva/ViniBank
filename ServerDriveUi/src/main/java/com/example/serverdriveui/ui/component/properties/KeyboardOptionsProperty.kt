package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

class KeyboardOptionsProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : KeyboardOptionsComponentProperty,
    BasePropertyData<KeyboardOptions>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "keyboardOptions",
        propertyValueTransformation = { it.toKeyboardOption() },
        defaultPropertyValue = KeyboardOptions.Default
    ) {
    override fun getKeyboardOptions() = getValue()
    override fun setKeyboardOptions(keyboardOptions: KeyboardOptions) = setValue(keyboardOptions)
}

private fun String?.toKeyboardOption() = when (this) {
    "Documento.CPF" -> KeyboardOptions(keyboardType = KeyboardType.Number)
    "Telefone" -> KeyboardOptions(keyboardType = KeyboardType.Phone)
    "Password", "Senha" -> KeyboardOptions(keyboardType = KeyboardType.Password)
    else -> KeyboardOptions.Default
}