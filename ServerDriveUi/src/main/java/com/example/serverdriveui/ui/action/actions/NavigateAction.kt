package com.example.serverdriveui.ui.action.actions

import com.example.router.routes.SdUiRoute
import com.example.router.routes.SdUiRouteData
import com.example.serverdriveui.GlobalStateManager
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.util.JsonUtil.getAsString
import com.example.serverdriveui.util.JsonUtil.getAsStringMap
import kotlinx.serialization.json.JsonObject

data class NavigationArg(
    val route: SdUiRoute,
    val requestedData: Map<String, String> = emptyMap(),
    val actionIdToRun: String? = null
)

class NavigateAction(
    val data: JsonObject,
    private val globalStateManager: GlobalStateManager,
) : Action {
    override fun execute() {
        val screenData = data.getAsStringMap("screenData")
        val requestedData = data.getAsStringMap("screenRequestData")
        val actionToRun = data.getAsString("actionId")

        val navigationArg = NavigationArg(
            route = SdUiRoute(
                data = SdUiRouteData.StartAsDefault(
                    flowId = data.getAsString("flow"),
                    screenData = screenData
                )
            ),
            requestedData = requestedData,
            actionIdToRun = actionToRun
        )

        globalStateManager.updateState(
            id = NAVIGATE_EFFECT_ID,
            data = navigationArg
        )
    }

    companion object {
        const val IDENTIFIER = "navigate"
        const val NAVIGATE_EFFECT_ID = "NAVIGATE_EFFECT_ID"
    }
}