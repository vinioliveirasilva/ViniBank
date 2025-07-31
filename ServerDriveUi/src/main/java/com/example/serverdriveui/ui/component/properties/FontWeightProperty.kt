package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString


interface FontWeightComponentProperty {
    @Composable
    fun getFontWeight(): FontWeight
}

data class FontWeightProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : FontWeightComponentProperty, BasePropertyData<FontWeight>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "fontWeight",
    transformToData = {
        when (it?.asString()?.lowercase()) {
            "thin" -> FontWeight.Thin
            "extra_light" -> FontWeight.ExtraLight
            "light" -> FontWeight.Light
            "normal" -> FontWeight.Normal
            "medium" -> FontWeight.Medium
            "semi_bold" -> FontWeight.SemiBold
            "bold" -> FontWeight.Bold
            "extra_bold" -> FontWeight.ExtraBold
            "black" -> FontWeight.Black
            else -> FontWeight.Normal
        }
    },
    defaultPropertyValue = FontWeight.Companion.Normal
) {
    @Composable
    override fun getFontWeight() = getValue()
}