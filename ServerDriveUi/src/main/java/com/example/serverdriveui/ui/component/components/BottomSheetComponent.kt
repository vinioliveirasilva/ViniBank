package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.manager.SdUiComponentPreview
import com.example.serverdriveui.ui.component.properties.VisibilityComponentProperty
import com.example.serverdriveui.ui.component.properties.VisibilityProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import com.google.gson.JsonObject

class BottomSheetComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
) : BaseComponent(model, properties, stateManager, validatorParser),
    VisibilityComponentProperty by VisibilityProperty(properties, stateManager) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        val sheetState = rememberModalBottomSheetState()
        if (getIsVisible().asValue()) {
            ModalBottomSheet(
                modifier = modifier,
                sheetState = sheetState,
                onDismissRequest = { setIsVisible(false) },
                windowInsets = WindowInsets(0, 0, 0, 50)
            ) {
                componentParser.parseList(model, componentStateManager = stateManager).forEach {
                    it.getComponentAsColumn(navController).invoke(this)
                }
            }
        }
    }

    companion object {
        const val IDENTIFIER = "bottomSheet"
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val jsonModel = """
        "type": "bottomSheet",
        "properties": [
            { "name": "shouldShow", "value" : "true" }
        ],
        "components" : [
            {
                "type": "text",
                "properties": [
                    { "name": "text", "value" :"Dialog text, can be anything, just for example" }
                ]
            }
        ]
    """

    SdUiComponentPreview(jsonModel)
}
