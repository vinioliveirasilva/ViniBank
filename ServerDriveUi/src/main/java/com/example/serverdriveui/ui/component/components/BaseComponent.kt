package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.InternalComponent
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingProperty
import com.example.serverdriveui.ui.component.properties.WeightComponentModifier
import com.example.serverdriveui.ui.component.properties.WeightModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

open class BaseComponent(
    model: JsonObject,
    properties: Map<String, PropertyModel>,
    componentStateManager: ComponentStateManager,
    validatorParser: ValidatorParser,
) : Component, InternalComponent,
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(
        properties,
        componentStateManager
    ),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(
        properties,
        componentStateManager
    ),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(properties, componentStateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(
        properties,
        componentStateManager
    ),
    WeightComponentModifier by WeightModifier(properties, componentStateManager) {

    override val internalModifier: Modifier
        @Composable
        get() = Modifier
            .then(horizontalFillTypeModifier)
            .then(verticalFillTypeModifier)
            .then(horizontalPaddingModifier)
            .then(verticalPaddingModifier)

    init {
        validatorParser.parse(model, componentStateManager)
            .forEach { validator -> validator.initialize() }
    }

    @Composable
    override fun getComponent(navController: NavHostController): @Composable (() -> Unit) = {
        Column {
            getInternalComponent(
                navController,
                internalModifier
            ).invoke()
        }
    }

    @Composable
    override fun getComponentAsRow(
        navController: NavHostController,
    ): @Composable (RowScope.() -> Unit) = {
        getInternalComponent(
            navController,
            internalModifier
                .then(weightModifier)
        ).invoke()
    }

    @Composable
    override fun getComponentAsColumn(
        navController: NavHostController,
    ): @Composable (ColumnScope.() -> Unit) = {
        getInternalComponent(
            navController,
            Modifier.then(horizontalFillTypeModifier)
                .then(verticalFillTypeModifier)
                .then(horizontalPaddingModifier)
                .then(verticalPaddingModifier)
                .then(weightModifier)
        ).invoke()
    }

    @Composable
    override fun getComponentLazyItemScope(
        navController: NavHostController,
    ): @Composable (LazyItemScope.() -> Unit) = {
        getInternalComponent(
            navController,
            internalModifier
        ).invoke()
    }

    override fun getComponentLazyListScope(
        navController: NavHostController,
    ): (LazyListScope.() -> Unit) = {
        item {
            getInternalComponent(
                navController,
                internalModifier
            ).invoke()
        }
    }
}