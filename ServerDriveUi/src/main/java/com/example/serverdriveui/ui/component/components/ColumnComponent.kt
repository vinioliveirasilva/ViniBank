package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.static.AlignmentComponentProperty
import com.example.serverdriveui.ui.component.properties.static.AlignmentProperty
import com.example.serverdriveui.ui.component.properties.static.ArrangementComponentProperty
import com.example.serverdriveui.ui.component.properties.static.ArrangementProperty
import com.example.serverdriveui.ui.component.properties.static.FillTypeComponentModifier
import com.example.serverdriveui.ui.component.properties.static.FillTypeModifier
import com.example.serverdriveui.ui.component.properties.static.PaddingComponentModifier
import com.example.serverdriveui.ui.component.properties.static.PaddingModifier
import com.example.serverdriveui.ui.component.properties.static.WeightComponentModifier
import com.example.serverdriveui.ui.component.properties.static.WeightModifier
import com.example.serverdriveui.ui.validator.manager.Validator

class ColumnComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val staticProperties: Map<String, String>,
    private val components: List<Component>,
    private val validators: List<Validator>,
) : Component,
    FillTypeComponentModifier by FillTypeModifier(staticProperties),
    PaddingComponentModifier by PaddingModifier(staticProperties),
    AlignmentComponentProperty by AlignmentProperty(staticProperties),
    ArrangementComponentProperty by ArrangementProperty(staticProperties),
    WeightComponentModifier by WeightModifier(staticProperties) {

    init {
        validators.forEach { it.initialize() }
    }

    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            Column(
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
                modifier = Modifier
                    .then(fillTypeModifier)
                    .then(paddingModifier)
                    .then(weightModifier())
            ) {
                components.forEach { it.getComponent(navController).invoke(this) }
            }
        }

    companion object {
        const val IDENTIFIER = "column"
    }
}