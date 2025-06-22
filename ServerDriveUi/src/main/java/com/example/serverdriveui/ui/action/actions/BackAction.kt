package com.example.serverdriveui.ui.action.actions

import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.action.manager.Action

class BackAction(val data: Map<String, String>) : Action {
    override fun execute(navController: NavHostController) {
        navController.navigateUp()
    }

    companion object {
        const val IDENTIFIER = "back"
    }
}