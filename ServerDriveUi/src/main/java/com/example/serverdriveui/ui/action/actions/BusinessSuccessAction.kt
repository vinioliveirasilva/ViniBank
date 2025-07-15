package com.example.serverdriveui.ui.action.actions

import android.app.Activity
import android.content.Intent
import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.util.JsonUtil.asString
import com.example.serverdriveui.util.JsonUtil.getAsMap
import kotlinx.serialization.json.JsonObject

class BusinessSuccessAction(val data: JsonObject) : Action {
    private val screenData = data.getAsMap("screenData")

    override fun execute(navController: NavHostController) {
        with(navController.context as Activity) {
            setResult(
                Activity.RESULT_OK,
                Intent().apply {
                    screenData.forEach {
                        putExtra(it.key, it.value.asString())
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