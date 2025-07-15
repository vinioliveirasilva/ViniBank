package com.example.serverdriveui.ui.validator.validators

import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.ui.validator.validators.generic.GenericConverterValidator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.jsonObject

class IntToDynamicComponentValidator(
    private val model: ValidatorModel,
    private val componentStateManager: ComponentStateManager,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main),
    private val componentParser: ComponentParser
) : Validator,
    GenericConverterValidator<Int, Component>(
        model = model,
        componentStateManager = componentStateManager,
        scope = scope,
        inputConverter = { it.toInt() },
        outputConverter = { componentParser.parse(data = it.jsonObject) },
        defaultOutput = componentParser.unknownComponent()
    ) {

    companion object {
        const val IDENTIFIER = "intToComponent"
    }
}