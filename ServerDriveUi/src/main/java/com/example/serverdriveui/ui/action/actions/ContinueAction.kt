package com.example.serverdriveui.ui.action.actions

import androidx.navigation.NavHostController
import com.example.serverdriveui.GlobalStateManager
import com.example.serverdriveui.SdUiDestinationModel
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.example.serverdriveui.util.JsonUtil.getAsMap
import com.example.serverdriveui.util.JsonUtil.getAsString
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class ContinueAction(
    val data: JsonObject,
    private val stateManager: ComponentStateManager,
    private val globalStateManager: GlobalStateManager,
) : Action {
    override fun execute(navController: NavHostController) {
        val newScreenData = buildJsonObject {
            data.getAsMap("screenData").forEach {
                put(it.key, it.value)
            }

            data.getAsMap("screenRequestData").forEach { requestData ->
                put(
                    requestData.value.asString(),
                    stateManager.getState<String>(requestData.key)?.value.orEmpty()
                )
            }
        }

        globalStateManager.updateState(
            id = CONTINUE_EFFECT_ID,
            data = SdUiDestinationModel(
                flowId = data.getAsString("flowId"),
                screenId = data.getAsString("nextScreenId"),
                screenData = newScreenData,
                lastScreenId = data.getAsString("currentScreenId")
            )
        )
    }

    companion object {
        const val IDENTIFIER = "continue"
        const val CONTINUE_EFFECT_ID = "CONTINUE_EFFECT_ID"
    }
}