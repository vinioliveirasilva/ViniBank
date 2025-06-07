package com.example.serverdriveui.ui.validator.manager

import com.example.serverdriveui.service.model.ValidatorModel

class ValidatorParser(private val validationManager: ValidatorManager) {
    fun parse(model: ValidatorModel?): Validator {
        return validationManager.getValidator(model)
    }

    //fun parse(actionModel: ActionModel?) : Action {
    //        if (actionModel == null) return actionManager.getAction("", mapOf())
    //        return actionManager.getAction(actionModel.type, actionModel.data)
    //    }
}