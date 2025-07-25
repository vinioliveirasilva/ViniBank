package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.component.properties.HorizontalAlignmentComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalAlignmentProperty
import com.example.serverdriveui.ui.component.properties.VerticalArrangementComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalArrangementProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.StateFlow

interface DynamicComponentProperty {
    fun getComponent(): StateFlow<Component?>
    fun setComponents(component: Component)
}

class DynamicComponentPropertyImpl(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val componentParser: ComponentParser,
) : DynamicComponentProperty,
    BasePropertyData<Component?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "components",
        propertyValueTransformation = {
            componentParser.parse(
                data = Gson().fromJson(
                    it,
                    JsonObject::class.java
                ),
                componentStateManager = stateManager
            )
        },
        defaultPropertyValue = null
    ) {
    override fun getComponent() = getValue()

    override fun setComponents(component: Component) = setValue(component)
}

class ColumnComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser),
    HorizontalAlignmentComponentProperty by HorizontalAlignmentProperty(
        properties,
        stateManager
    ),
    VerticalArrangementComponentProperty by VerticalArrangementProperty(
        properties,
        stateManager
    ), DynamicComponentProperty by DynamicComponentPropertyImpl(
        properties,
        stateManager,
        componentParser
    ) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable () -> Unit = {
        val action = actionParser.parse(model, componentStateManager = stateManager)
        val actionModifier = action?.let { Modifier.clickable{ it.execute(navController) } } ?: Modifier
        val dynamicComponents = getComponent().asValue()

        Column(
            verticalArrangement = getVerticalArrangement().asValue(),
            horizontalAlignment = getHorizontalAlignment().asValue(),
            modifier = modifier
                .then(actionModifier)
        ) {
            dynamicComponents?.getComponentAsColumn(navController)?.invoke(this)
                ?: componentParser.parseList(data = model, componentStateManager = stateManager)
                    .forEach {
                        it.getComponentAsColumn(navController).invoke(this)
                    }
        }
    }

    companion object {
        const val IDENTIFIER = "column"
    }
}