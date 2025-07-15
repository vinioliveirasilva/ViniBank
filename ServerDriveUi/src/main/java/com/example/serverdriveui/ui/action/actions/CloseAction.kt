package com.example.serverdriveui.ui.action.actions

import android.app.Activity
import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.action.manager.Action

class CloseAction(val data: Map<String, String>) : Action {
    override fun execute(navController: NavHostController) {
        (navController.context as Activity).finish()
    }

    companion object {
        const val IDENTIFIER = "close"
    }
}