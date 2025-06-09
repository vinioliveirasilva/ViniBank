package com.example.serverdriveui.ui.component.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.component.components.ButtonComponent
import com.example.serverdriveui.ui.component.components.OutlinedButtonComponent
import com.example.serverdriveui.ui.component.components.ColumnComponent
import com.example.serverdriveui.ui.component.components.LottieAnimationComponent
import com.example.serverdriveui.ui.component.components.RowComponent
import com.example.serverdriveui.ui.component.components.SpacerComponent
import com.example.serverdriveui.ui.component.components.TextComponent
import com.example.serverdriveui.ui.component.components.TopAppBarComponent
import com.example.serverdriveui.ui.component.components.createpassword.CreatePasswordComponent
import com.example.serverdriveui.ui.component.components.textinput.OutlinedTextInputComponent
import com.example.serverdriveui.ui.component.components.textinput.TextInputComponent
import com.example.serverdriveui.ui.validator.manager.Validator
import com.google.gson.JsonObject
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class ComponentManager(private val koinScope: Scope) {

    fun getComponent(identifier: String, data: JsonObject): Component = when (identifier) {
        ButtonComponent.IDENTIFIER -> koinScope.get<ButtonComponent> { parametersOf(data) }
        OutlinedButtonComponent.IDENTIFIER -> koinScope.get<OutlinedButtonComponent> { parametersOf(data) }
        ColumnComponent.IDENTIFIER -> koinScope.get<ColumnComponent> { parametersOf(data) }
        RowComponent.IDENTIFIER -> koinScope.get<RowComponent> { parametersOf(data) }
        TextComponent.IDENTIFIER -> koinScope.get<TextComponent> { parametersOf(data) }
        SpacerComponent.IDENTIFIER -> koinScope.get<SpacerComponent> { parametersOf(data) }
        TextInputComponent.IDENTIFIER -> koinScope.get<TextInputComponent> { parametersOf(data) }
        else -> unknownComponent()
    }

    fun getComponent(
        identifier: String,
        dynamicProperties: List<PropertyModel>,
        staticProperties: Map<String, String>,
        innerComponents: List<Component>,
        action: Action,
        validator: List<Validator>
    ): Component = when (identifier) {
        ButtonComponent.IDENTIFIER -> koinScope.get<ButtonComponent> { parametersOf(dynamicProperties, staticProperties, action, validator) }
        OutlinedButtonComponent.IDENTIFIER -> koinScope.get<OutlinedButtonComponent> { parametersOf(dynamicProperties, staticProperties, action, validator) }
        ColumnComponent.IDENTIFIER -> koinScope.get<ColumnComponent> { parametersOf(dynamicProperties, staticProperties, innerComponents, validator) }
        RowComponent.IDENTIFIER -> koinScope.get<RowComponent> { parametersOf(dynamicProperties, staticProperties, innerComponents, validator) }
        TextComponent.IDENTIFIER -> koinScope.get<TextComponent> { parametersOf(dynamicProperties, staticProperties, validator) }
        TopAppBarComponent.IDENTIFIER -> koinScope.get<TopAppBarComponent> { parametersOf(dynamicProperties, staticProperties, validator) }
        SpacerComponent.IDENTIFIER -> koinScope.get<SpacerComponent> { parametersOf(staticProperties, validator) }
        TextInputComponent.IDENTIFIER -> koinScope.get<TextInputComponent> { parametersOf(dynamicProperties, staticProperties, validator) }
        OutlinedTextInputComponent.IDENTIFIER -> koinScope.get<OutlinedTextInputComponent> { parametersOf(dynamicProperties, staticProperties, validator) }
        LottieAnimationComponent.IDENTIFIER -> koinScope.get<LottieAnimationComponent> { parametersOf(dynamicProperties, staticProperties, action, validator) }
        CreatePasswordComponent.IDENTIFIER -> koinScope.get<CreatePasswordComponent> { parametersOf(dynamicProperties, staticProperties, action, validator) }
        else -> unknownComponent()
    }

    private fun unknownComponent() = object : Component {
        @Composable
        override fun getComponent(navController: NavHostController): @Composable (ColumnScope.() -> Unit) =
            {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.Red)
                        .padding(5.dp)
                        .background(Color.White)
                ) {
                    Text("Componente desconhecido")
                }
            }
    }
}