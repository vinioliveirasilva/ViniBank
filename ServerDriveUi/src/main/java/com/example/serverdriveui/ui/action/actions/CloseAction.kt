package com.example.serverdriveui.ui.action.actions

import android.app.Activity
import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.action.manager.Action
import kotlinx.serialization.json.JsonObject

class CloseAction(val data: JsonObject) : Action {
    override fun execute(navController: NavHostController) {
        (navController.context as Activity).finish()
    }

    companion object {
        const val IDENTIFIER = "close"
    }
}