package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.SizeComponentModifier
import com.example.serverdriveui.ui.component.properties.SizeModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

class SpacerComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser
) : BaseComponent(model, properties, stateManager, validatorParser),
    SizeComponentModifier by SizeModifier(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        Spacer(modifier = modifier.then(sizeModifier))
    }

    companion object {
        const val IDENTIFIER = "spacer"
    }
}


@Preview(showBackground = true)
@Composable
fun abc() {
    var isChecked = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(true) {
                isChecked.value = !isChecked.value
                println("salve")
            }
    ) {
        Checkbox(
            checked = isChecked.value,
            onCheckedChange = {
            }
        )
    }
}
