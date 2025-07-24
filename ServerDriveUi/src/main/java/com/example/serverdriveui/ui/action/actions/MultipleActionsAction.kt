package com.example.serverdriveui.ui.action.actions

import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.action.manager.ActionParser
import kotlinx.serialization.json.JsonObject

class MultipleActionsAction(
    private val data: JsonObject,
    private val actionParser: ActionParser,
) : Action {

    private val actions = actionParser.parseActions(data).values

    override fun execute(navController: NavHostController) {
        super.execute(navController)
        actions.forEach { action -> action.execute(navController) }
    }

    companion object {
        const val IDENTIFIER = "multipleActions"
    }
}