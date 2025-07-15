package com.example.serverdriveui.ui.action.actions

import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.state.ComponentStateManager

class ToIntAction(
    val data: Map<String, String>,
    private val stateManager: ComponentStateManager,
) : Action {
    private val externalIdToChange = data["id"].orEmpty()
    private val newValue = data["value"]?.toIntOrNull() ?: 0

    override fun execute(navController: NavHostController) {
        stateManager.updateState(externalIdToChange, newValue)
    }

    companion object {
        const val IDENTIFIER = "toInt"
    }
}