package com.example.serverdriveui.ui.actions

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.actions.manager.Action
import com.google.gson.JsonParser

class BusinessSuccessAction(val data: Map<String, String>) : Action {
    private val screenData = data["screenData"]?.let { JsonParser.parseString(it) }?.asJsonObject?.asMap().orEmpty()

    override fun execute(navController: NavHostController) {
        with(navController.context as Activity) {
            setResult(
                Activity.RESULT_OK,
                Intent().apply {
                    screenData.forEach {
                        putExtra(it.key, it.value.asString)
                    }
                }
            )
            finish()
        }
    }

    companion object {
        const val IDENTIFIER = "businessSuccess"
    }
}