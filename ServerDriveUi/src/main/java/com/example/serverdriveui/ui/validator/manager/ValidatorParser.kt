package com.example.serverdriveui.ui.validator.manager

import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ValidatorParser(private val koinScope: Scope) {
    fun parse(
        model: JsonObject,
        componentStateManager: ComponentStateManager
    ): List<Validator> {
        return (model.getAsJsonArray("validators") ?: return emptyList()).map {
            Gson().fromJson(it, ValidatorModel::class.java)
        }.map { validator ->
            koinScope.getOrNull<Validator>(named(validator.type)) { parametersOf(validator, componentStateManager) }
                ?: throw IllegalArgumentException("Validator not found")
        }
    }
}