package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class KeyboardOptionsProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : KeyboardOptionsComponentProperty,
    BasePropertyData<KeyboardOptions>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "keyboardOptions",
        propertyValueTransformation = { it.toKeyboardOption() },
        defaultPropertyValue = KeyboardOptionsOption.Default.id,
        scope = scope
    ) {
    override fun getKeyboardOptions() = getValue()
    override fun setKeyboardOptions(keyboardOptions: KeyboardOptionsOption) = setValue(keyboardOptions.id)
}

enum class KeyboardOptionsOption(val id: String, val keyboardOptions: KeyboardOptions) {
    Default("Default", KeyboardOptions.Default),
    Number("Number", KeyboardOptions(keyboardType = KeyboardType.Number)),
    Phone("Phone", KeyboardOptions(keyboardType = KeyboardType.Phone)),
    Password("Password", KeyboardOptions(keyboardType = KeyboardType.Password)),
}

private fun String?.toKeyboardOption() =
    KeyboardOptionsOption.entries.firstOrNull { it.id == this }?.keyboardOptions
        ?: KeyboardOptionsOption.Default.keyboardOptions