package com.example.serverdriveui.ui.action.actions

import androidx.navigation.NavHostController
import com.example.router.routes.SdUiRoute
import com.example.router.routes.SdUiRouteData
import com.example.serverdriveui.GlobalStateManager
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.util.JsonUtil.asString
import com.example.serverdriveui.util.JsonUtil.getAsMap
import com.example.serverdriveui.util.JsonUtil.getAsString
import kotlinx.serialization.json.JsonObject

class NavigateAction(
    val data: JsonObject,
    private val globalStateManager: GlobalStateManager,
) : Action {
    override fun execute(navController: NavHostController) {
        val newScreenData = (data.getAsMap("screenData") + data.getAsMap("screenRequestData"))
            .mapValues { it.value.asString() }
        globalStateManager.updateState(
            id = NAVIGATE_EFFECT_ID,
            data = SdUiRoute(
                data = SdUiRouteData.StartAsDefault(
                    flowId = data.getAsString("flow"),
                    screenData = newScreenData
                )
            )
        )
    }

    companion object {
        const val IDENTIFIER = "navigate"
        const val NAVIGATE_EFFECT_ID = "NAVIGATE_EFFECT_ID"
    }
}