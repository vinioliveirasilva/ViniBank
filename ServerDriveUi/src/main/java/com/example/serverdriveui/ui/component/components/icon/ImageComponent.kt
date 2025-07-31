package com.example.serverdriveui.ui.component.components.icon

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.components.icon.properties.IconDrawableComponent
import com.example.serverdriveui.ui.component.components.icon.properties.IconDrawableProperty
import com.example.serverdriveui.ui.component.components.icon.properties.IconNameComponent
import com.example.serverdriveui.ui.component.components.icon.properties.IconNameProperty
import com.example.serverdriveui.ui.component.properties.SizeComponentModifier
import com.example.serverdriveui.ui.component.properties.SizeModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import kotlinx.serialization.json.JsonObject

class ImageComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    SizeComponentModifier by SizeModifier(properties, stateManager),
    IconNameComponent by IconNameProperty(properties, stateManager),
    IconDrawableComponent by IconDrawableProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        icon?.let {
            Image(
                modifier = modifier.then(sizeModifier),
                imageVector = it, contentDescription = null
            )
        } ?: drawableIcon?.let {
            Image(
                modifier = modifier.then(sizeModifier),
                painter = painterResource(it), contentDescription = null
            )
        }
    }

    companion object {
        const val IDENTIFIER = "image"
    }
}

@Preview(showBackground = true)
@Composable
private fun IconComponentPreview() {

//    SdUiComponentPreview(
//        """
//            "type": "image",
//            "properties": [
//                {
//                    "name": "iconDrawable",
//                    "value": "Mastercard"
//                },
//                {
//                    "name": "size",
//                    "value": "48"
//                }
//            ]
//        """
//    )
}