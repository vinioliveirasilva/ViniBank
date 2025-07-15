package com.example.serverdriveui.ui.validator.validators

import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.validators.generic.GenericConverterValidator
import com.example.serverdriveui.util.JsonUtil.asString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class IntToStringValidator(
    private val model: ValidatorModel,
    private val componentStateManager: ComponentStateManager,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main),
) : GenericConverterValidator<Int, String>(
    model = model,
    componentStateManager = componentStateManager,
    scope = scope,
    inputConverter = { it.toInt() },
    outputConverter = { it.asString() },
    defaultOutput = ""
) {
    companion object {
        const val IDENTIFIER = "intToString"
    }
}