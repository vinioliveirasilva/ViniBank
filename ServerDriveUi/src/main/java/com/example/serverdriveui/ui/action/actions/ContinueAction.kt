package com.example.serverdriveui.ui.action.actions

import androidx.navigation.NavHostController
import com.example.serverdriveui.GlobalStateManager
import com.example.serverdriveui.SdUiDestinationModel
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.vini.common.or

class ContinueAction(
    val data: Map<String, String>,
    private val stateManager: ComponentStateManager,
    private val globalStateManager: GlobalStateManager,
) : Action {

    private val nextScreenId = data["nextScreenId"].orEmpty()
    private val currentScreenId = data["currentScreenId"].orEmpty()
    private val flowId = data["flowId"].orEmpty()

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
        globalStateManager.updateState(CONTINUE_EFFECT_ID, SdUiDestinationModel(flowId, nextScreenId, screenData.toString(), currentScreenId))
    }

    companion object {
        const val IDENTIFIER = "continue"
        const val CONTINUE_EFFECT_ID = "CONTINUE_EFFECT_ID"
    }
}