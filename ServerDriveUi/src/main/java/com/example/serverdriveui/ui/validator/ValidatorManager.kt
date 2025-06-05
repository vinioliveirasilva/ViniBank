package com.example.serverdriveui.ui.validator

import com.example.serverdriveui.service.model.ValidatorModel
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class ValidatorManager(private val koinScope: Scope) {
    fun getValidator(model: ValidatorModel?) : Validator = when(model?.type) {
        MinLengthValidator.IDENTIFIER -> koinScope.get<MinLengthValidator> { parametersOf(model) }
        AllTrueValidator.IDENTIFIER -> koinScope.get<AllTrueValidator> { parametersOf(model) }
        else -> throw IllegalArgumentException("Validator not found")
    }
}