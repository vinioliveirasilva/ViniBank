package com.example.serverdriveui.ui.component.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.properties.TextAlignComponentProperty
import com.example.serverdriveui.ui.component.properties.TextAlignProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.StringUtil.toAnnotatedStringFromHtml
import com.example.serverdriveui.util.asValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.JsonObject

data class TextComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val actionParser: ActionParser,
    private val scope: CoroutineScope
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser, scope),
    TextComponentProperty by TextProperty(properties, stateManager, scope),
    TextAlignComponentProperty by TextAlignProperty(properties, stateManager, scope) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        val text = getText().asValue()
        val textAlign = getTextAlign().asValue()

        Text(
            textAlign = textAlign,
            modifier = modifier,
            text = text.toAnnotatedStringFromHtml()
        )
    }

    companion object {
        const val IDENTIFIER = "text"
    }
}