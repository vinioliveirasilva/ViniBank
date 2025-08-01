package com.example.serverdriveui.ui.validator.validators.generic

import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonElement

open class GenericConverterValidator<Input, Output>(
    private val model: ValidatorModel,
    private val componentStateManager: ComponentStateManager,
    private val scope: CoroutineScope,
    private val inputConverter: (String) -> Input,
    private val outputConverter: (JsonElement) -> Output,
    private val defaultOutput: Output,
) : Validator {
    val parsedData = model.data
        .mapKeys { inputConverter(it.key) }
        .mapValues { outputConverter(it.value) }

    override fun initialize() {
        model.required.forEach { reqId ->
            scope.launch {
                componentStateManager.getState<Input>(reqId)?.collect { result ->
                    val converted = parsedData[result] ?: defaultOutput
                    componentStateManager.registerState(model.id, converted)
                    validate(converted)
                }
            }
        }
    }

    override fun close() {
        scope.cancel()
    }

    private fun validate(stringResult: Output) {
        componentStateManager.updateState(
            model.id,
            stringResult
        )
    }
}