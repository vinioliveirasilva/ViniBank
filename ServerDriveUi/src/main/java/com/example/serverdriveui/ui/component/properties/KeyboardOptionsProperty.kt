package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString

class KeyboardOptionsProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : KeyboardOptionsComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "keyboardOptions",
        defaultPropertyValue = KeyboardOptionsOption.Default.id,
        transformToData = { it?.asString() }
    ) {

    @Composable
    override fun getKeyboardOptions() = getValue().toOption().keyboardOptions

    override fun setKeyboardOptions(keyboardOptions: KeyboardOptionsOption) = setValue(keyboardOptions.id)
}

enum class KeyboardOptionsOption(val id: String, val keyboardOptions: KeyboardOptions) {
    Default("Default", KeyboardOptions.Default),
    Number("Number", KeyboardOptions(keyboardType = KeyboardType.Number)),
    Phone("Phone", KeyboardOptions(keyboardType = KeyboardType.Phone)),
    Password("Password", KeyboardOptions(keyboardType = KeyboardType.Password)),
}

private fun String?.toOption() =
    KeyboardOptionsOption.entries.firstOrNull { it.id == this }
        ?: KeyboardOptionsOption.Default