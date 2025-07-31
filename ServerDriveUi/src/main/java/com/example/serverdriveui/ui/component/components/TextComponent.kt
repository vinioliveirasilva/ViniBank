package com.example.serverdriveui.ui.component.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.properties.FontSizeComponentProperty
import com.example.serverdriveui.ui.component.properties.FontSizeProperty
import com.example.serverdriveui.ui.component.properties.FontWeightComponentProperty
import com.example.serverdriveui.ui.component.properties.FontWeightProperty
import com.example.serverdriveui.ui.component.properties.TextAlignComponentProperty
import com.example.serverdriveui.ui.component.properties.TextAlignProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.StringUtil.toAnnotatedStringFromHtml
import kotlinx.serialization.json.JsonObject

data class TextComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    TextComponentProperty by TextProperty(properties, stateManager),
    TextAlignComponentProperty by TextAlignProperty(properties, stateManager),
    FontSizeComponentProperty by FontSizeProperty(properties, stateManager),
    FontWeightComponentProperty by FontWeightProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        Text(
            textAlign = getTextAlign(),
            modifier = modifier,
            fontSize = getFontSize().sp,
            fontWeight = getFontWeight(),
            text = getText().toAnnotatedStringFromHtml()
        )
    }

    companion object {
        const val IDENTIFIER = "text"
    }
}