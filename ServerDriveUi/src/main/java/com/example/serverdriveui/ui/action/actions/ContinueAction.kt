package com.example.serverdriveui.ui.action.actions

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
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.TimeMark
import kotlin.time.TimeSource

class ContinueAction(
    val data: JsonObject,
    private val stateManager: ComponentStateManager,
    private val globalStateManager: GlobalStateManager,
    private val timeSource: TimeSource = TimeSource.Monotonic,
) : Action {
    private val debounceTime = 200.milliseconds
    private var timeMark: TimeMark? = null

    override fun execute() {
        timeMark?.run {
            if (elapsedNow() < debounceTime) {
                return
            }
        }
        timeMark = timeSource.markNow()

        val newScreenData = buildJsonObject {
            data.getAsMap("screenData").forEach {
                put(it.key, it.value)
            }

            data.getAsMap("screenRequestData").forEach { requestData ->
                put(
                    requestData.value.asString(),
                    stateManager.getState<String>(requestData.key)?.value
                )
            }
        }

        globalStateManager.setDestination(
            SdUiDestinationModel(
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