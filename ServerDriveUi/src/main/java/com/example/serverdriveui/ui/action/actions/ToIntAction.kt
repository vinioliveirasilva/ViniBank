package com.example.serverdriveui.ui.action.actions

import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.getAsString
import kotlinx.serialization.json.JsonObject

class ToIntAction(
    val data: JsonObject,
    private val stateManager: ComponentStateManager,
) : Action {
    private val externalIdToChange = data.getAsString("id")
    private val newValue = data.getAsString("value")

    override fun execute() {
        stateManager.updateState(externalIdToChange, newValue)
    }

    companion object {
        const val IDENTIFIER = "toInt"
    }
}