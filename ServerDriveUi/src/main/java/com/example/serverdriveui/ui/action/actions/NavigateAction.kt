package com.example.serverdriveui.ui.action.actions

import androidx.navigation.NavHostController
import com.example.router.routes.SdUiRoute
import com.example.router.routes.SdUiRouteData
import com.example.serverdriveui.GlobalStateManager
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.vini.common.or

class NavigateAction(
    val data: Map<String, String>,
    private val stateManager: ComponentStateManager,
    private val globalStateManager: GlobalStateManager,
) : Action {

    private val flowId = data["flow"].orEmpty()

    private val screenData = data["screenData"]?.let {
        JsonParser.parseString(it)
    }?.asJsonObject.or(JsonObject())

    private val screenRequestData = data["screenRequestData"]?.let {
        JsonParser.parseString(it)
    }?.asJsonObject?.asMap().orEmpty()

    override fun execute(navController: NavHostController) {
        screenRequestData.forEach { requestData ->
            screenData.addProperty(
                requestData.value.asString,
                stateManager.getState<String>(requestData.key)?.value.orEmpty()
            )
        }
        globalStateManager.updateState(
            NAVIGATE_EFFECT_ID,
            SdUiRoute(SdUiRouteData.StartAsDefault(flowId, /* TODO screenData */))
        )
    }

    companion object {
        const val IDENTIFIER = "navigate"
        const val NAVIGATE_EFFECT_ID = "NAVIGATE_EFFECT_ID"
    }
}