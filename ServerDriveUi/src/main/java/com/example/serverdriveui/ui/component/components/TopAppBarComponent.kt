package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextAlignComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextAlignProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.util.asValue

data class TopAppBarComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val validators: List<Validator>,
    private val action: Action,
    private val stateManager: ComponentStateManager,
) : Component,
    TextComponentProperty by TextProperty(dynamicProperties, stateManager),
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(dynamicProperties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(
        dynamicProperties,
        stateManager
    ),
    TextAlignComponentProperty by TextAlignProperty(dynamicProperties, stateManager) {

    init {
        validators.forEach { it.initialize() }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            val text = getText().asValue()
            val textAlign = getTextAlign().asValue()

            CenterAlignedTopAppBar(
                modifier = Modifier
                    .then(horizontalFillTypeModifier)
                    .then(verticalFillTypeModifier),
                title = {
                    Text(
                        textAlign = textAlign,
                        modifier = Modifier
                            .then(horizontalFillTypeModifier)
                            .then(verticalFillTypeModifier),
                        text = text
                    )
                }
            )
        }

    companion object {
        const val IDENTIFIER = "topAppBar"
    }
}

@Preview
@Composable
fun TopAppBarComponentPreview() {
    val navHostController = NavHostController(LocalContext.current)
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBarComponent(
            listOf(PropertyModel(name = "text", value = "Title")),
            emptyList(),
            object : Action { override fun execute(navController: NavHostController) {} },
            ComponentStateManager(),
        ).getComponent(navController = navHostController).invoke(this)
    }
}