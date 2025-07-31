package com.example.serverdriveui.ui.action.actions

import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.getAsBoolean
import com.example.serverdriveui.util.JsonUtil.getAsString
import kotlinx.serialization.json.JsonObject

class ToBooleanAction(
    val data: JsonObject,
    private val stateManager: ComponentStateManager,
) : Action {
    private val externalIdToChange = data.getAsString("id")
    private val newValue = data.getAsBoolean("value")

    override fun execute() {
        stateManager.updateState(externalIdToChange, newValue)
    }

    companion object {
        const val IDENTIFIER = "toBoolean"
    }
}