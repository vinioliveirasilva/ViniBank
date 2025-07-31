package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.PropertyOptions.KeyboardOptionsOption

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

private fun String?.toOption() =
    KeyboardOptionsOption.entries.firstOrNull { it.id == this }
        ?: KeyboardOptionsOption.Default

interface KeyboardOptionsComponentProperty {
    @Composable
    fun getKeyboardOptions(): KeyboardOptions
    fun setKeyboardOptions(keyboardOptions: KeyboardOptionsOption)
}