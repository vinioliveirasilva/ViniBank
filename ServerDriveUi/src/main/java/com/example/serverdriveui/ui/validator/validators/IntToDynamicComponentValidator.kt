package com.example.serverdriveui.ui.validator.validators

import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.ui.validator.validators.generic.GenericConverterValidator
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

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
        outputConverter = { componentParser.parse(
            data = Gson().fromJson(
                it,
                JsonObject::class.java
            ),
            componentStateManager = componentStateManager
        ) },
        defaultOutput = componentParser.unknownComponent()
    ) {

    companion object {
        const val IDENTIFIER = "intToComponent"
    }
}