package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.TextAlignComponentProperty
import com.example.serverdriveui.ui.component.properties.TextAlignProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import com.google.gson.JsonObject

data class TextComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser
) : BaseComponent(model, validatorParser),
    TextComponentProperty by TextProperty(properties, stateManager),
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(properties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(properties, stateManager),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(properties, stateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(properties, stateManager),
    TextAlignComponentProperty by TextAlignProperty(properties, stateManager) {

    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            val text = getText().asValue()
            val textAlign = getTextAlign().asValue()

            Text(
                textAlign = textAlign,
                modifier = Modifier
                    .then(horizontalFillTypeModifier)
                    .then(verticalFillTypeModifier)
                    .then(horizontalPaddingModifier)
                    .then(verticalPaddingModifier),
                text = text
            )
        }

    companion object {
        const val IDENTIFIER = "text"
    }
}