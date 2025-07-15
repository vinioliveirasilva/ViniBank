package com.example.serverdriveui.ui.component.components

import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.component.properties.VisibilityComponentProperty
import com.example.serverdriveui.ui.component.properties.VisibilityProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class SnackBarComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser),
    TextComponentProperty by TextProperty(properties, stateManager),
    VisibilityComponentProperty by VisibilityProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        val snackBarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val text = getText().asValue()

        //TODO Adicionar os components e as actions

        LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
            setIsVisible(false)
            getIsVisible().filter { it }.map {
                when(snackBarHostState.showSnackbar(text)) {
                    SnackbarResult.Dismissed -> {
                        setIsVisible(false)
                    }
                    SnackbarResult.ActionPerformed -> {
                        setIsVisible(false)
                    }
                }
            }.launchIn(scope)
        }

        SnackbarHost(hostState = snackBarHostState) { Snackbar(it) }
    }

    companion object {
        const val IDENTIFIER = "snackBar"
    }
}