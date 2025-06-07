package com.example.serverdriveui.ui.validator.manager

import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.validator.AllTrueValidator
import com.example.serverdriveui.ui.validator.EmailValidator
import com.example.serverdriveui.ui.validator.MinLengthValidator
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope

class ValidatorManager(private val koinScope: Scope) {
    fun getValidator(model: ValidatorModel?) : Validator = when(model?.type) {
        MinLengthValidator.Companion.IDENTIFIER -> koinScope.get<MinLengthValidator> { parametersOf(model) }
        AllTrueValidator.Companion.IDENTIFIER -> koinScope.get<AllTrueValidator> { parametersOf(model) }
        EmailValidator.Companion.IDENTIFIER -> koinScope.get<EmailValidator> { parametersOf(model) }
        else -> throw IllegalArgumentException("Validator not found")
    }
}