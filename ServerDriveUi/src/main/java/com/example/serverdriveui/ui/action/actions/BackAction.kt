package com.example.serverdriveui.ui.action.actions

import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.action.manager.Action
import kotlinx.serialization.json.JsonObject

class BackAction(val data: JsonObject) : Action {
    override fun execute(navController: NavHostController) {
        super.execute(navController)
        navController.navigateUp()
    }

    companion object {
        const val IDENTIFIER = "back"
    }
}