package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.InternalComponent
import com.example.serverdriveui.ui.component.properties.HeightComponentProperty
import com.example.serverdriveui.ui.component.properties.HeightProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingProperty
import com.example.serverdriveui.ui.component.properties.VisibilityComponentProperty
import com.example.serverdriveui.ui.component.properties.VisibilityProperty
import com.example.serverdriveui.ui.component.properties.WeightComponentModifier
import com.example.serverdriveui.ui.component.properties.WeightModifier
import com.example.serverdriveui.ui.component.properties.WidthComponentProperty
import com.example.serverdriveui.ui.component.properties.WidthProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import kotlinx.serialization.json.JsonObject

open class BaseComponent(
    model: JsonObject,
    properties: Map<String, PropertyModel>,
    stateManager: ComponentStateManager,
    validatorParser: ValidatorParser,
    actionParser: ActionParser
) : Component, InternalComponent,
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(properties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(properties, stateManager),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(properties, stateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(properties, stateManager),
    HeightComponentProperty by HeightProperty(properties, stateManager),
    WidthComponentProperty by WidthProperty(properties, stateManager),
    WeightComponentModifier by WeightModifier(properties, stateManager),
    VisibilityComponentProperty by VisibilityProperty(properties, stateManager)
{

    val actions: Map<String, Action> = actionParser?.parseActions(model) ?: emptyMap()

    init {
        actions.forEach { (id, action) -> action.initialize() }
        validatorParser.parse(model).forEach { validator -> validator.initialize() }
    }

    @Composable
    private fun IntermediateComponent(navController: NavHostController, modifier: Modifier) {
        if (getIsVisible().asValue()) {
            getInternalComponent(navController, modifier).invoke()
        }
    }

    override val internalModifier: Modifier
        @Composable
        get() = Modifier
            .then(horizontalFillTypeModifier)
            .then(verticalFillTypeModifier)
            .then(horizontalPaddingModifier)
            .then(verticalPaddingModifier)
            .then(heightModifier)
            .then(widthModifier)

    @Composable
    override fun getComponent(navController: NavHostController): @Composable (() -> Unit) = {
        IntermediateComponent(
            navController,
            internalModifier
        )
    }

    @Composable
    override fun getComponentAsRow(
        navController: NavHostController,
    ): @Composable (RowScope.() -> Unit) = {
        IntermediateComponent(
            navController,
            internalModifier
                .then(weightModifier)
        )
    }

    @Composable
    override fun getComponentAsColumn(
        navController: NavHostController,
    ): @Composable (ColumnScope.() -> Unit) = {
        IntermediateComponent(
            navController,
            internalModifier
                .then(weightModifier)
        )
    }

    @Composable
    override fun getComponentLazyItemScope(
        navController: NavHostController,
    ): @Composable (LazyItemScope.() -> Unit) = {
        IntermediateComponent(
            navController,
            internalModifier
        )
    }

    override fun getComponentLazyListScope(
        navController: NavHostController,
    ): (LazyListScope.() -> Unit) = {
        item {
            IntermediateComponent(
                navController,
                internalModifier
            )
        }
    }
}