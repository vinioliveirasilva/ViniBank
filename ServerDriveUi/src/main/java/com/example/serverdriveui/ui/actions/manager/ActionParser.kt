package com.example.serverdriveui.ui.actions.manager

import com.example.serverdriveui.service.model.ActionModel

class ActionParser(val actionManager: ActionManager) {
    /*
    fun parse(data: JsonObject): Action {
        val action = data.get("action")

        if (action == null) return actionManager.getAction("", JsonObject())
        val (type, dataObject) = SdUiJsonParseExtension.parseBaseData(action.asJsonObject)

        return actionManager.getAction(type, dataObject)
    }

     */

    fun parse(actionModel: ActionModel?) : Action {
        if (actionModel == null) return actionManager.getAction("", mapOf())
        return actionManager.getAction(actionModel.type, actionModel.data)
    }
}