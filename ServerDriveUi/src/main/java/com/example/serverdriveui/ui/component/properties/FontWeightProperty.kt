package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.FontWeightOption


interface FontWeightComponentProperty {
    @Composable
    fun getFontWeight(): FontWeight
}

data class FontWeightProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : FontWeightComponentProperty, BasePropertyData<String>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "fontWeight",
    transformToData = { it?.asString() },
    defaultPropertyValue = FontWeightOption.Default.name
) {
    @Composable
    override fun getFontWeight() = FontWeightOption.valueOf(getValue()).toFontWeight()

    private fun FontWeightOption.toFontWeight(): FontWeight = when(this) {
        FontWeightOption.Thin -> FontWeight.Thin
        FontWeightOption.ExtraLight -> FontWeight.ExtraLight
        FontWeightOption.Light -> FontWeight.Light
        FontWeightOption.Normal -> FontWeight.Normal
        FontWeightOption.Medium -> FontWeight.Medium
        FontWeightOption.SemiBold -> FontWeight.SemiBold
        FontWeightOption.Bold -> FontWeight.Bold
        FontWeightOption.ExtraBold -> FontWeight.ExtraBold
        FontWeightOption.Black -> FontWeight.Black
        FontWeightOption.Default -> FontWeight.Normal
    }
}