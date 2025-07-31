package com.example.serverdriveui.ui.validator.manager

import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.util.JsonUtil.getAsTypedArray
import kotlinx.serialization.json.JsonObject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ValidatorParser(private val koinScope: Scope) : AutoCloseable {

    private val cache: MutableMap<ValidatorModel, Validator?> = mutableMapOf()

    override fun close() {
        cache.values.forEach { it?.close() }
    }

    fun parse(
        model: JsonObject,
    ): List<Validator> {
        return model.getAsTypedArray<ValidatorModel>(TAG_TO_PARSE).map { validator ->
            cache[validator] ?: koinScope.getOrNull<Validator>(named(validator.type)) {
                parametersOf(validator)
            }.also { cache[validator] = it }
            ?: throw IllegalArgumentException("Validator not found")
        }
    }

    private companion object {
        const val TAG_TO_PARSE = "validators"
    }
}