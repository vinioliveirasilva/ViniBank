package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.KeyboardOptionsOption

class KeyboardOptionsProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : KeyboardOptionsComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "keyboardOptions",
        defaultPropertyValue = KeyboardOptionsOption.Default.name,
        transformToData = { it?.asString() }
    ) {

    @Composable
    override fun getKeyboardOptions() = KeyboardOptionsOption.valueOf(getValue()).toKeyboardOptions()

    override fun setKeyboardOptions(keyboardOptions: KeyboardOptionsOption) =
        setValue(keyboardOptions.name)

    private fun KeyboardOptionsOption.toKeyboardOptions() = when(this) {
        KeyboardOptionsOption.Default -> KeyboardOptions.Default
        KeyboardOptionsOption.Number -> KeyboardOptions(keyboardType = KeyboardType.Number)
        KeyboardOptionsOption.Phone -> KeyboardOptions(keyboardType = KeyboardType.Phone)
        KeyboardOptionsOption.Password -> KeyboardOptions(keyboardType = KeyboardType.Password)

    }
}

interface KeyboardOptionsComponentProperty {
    @Composable
    fun getKeyboardOptions(): KeyboardOptions
    fun setKeyboardOptions(keyboardOptions: KeyboardOptionsOption)
}