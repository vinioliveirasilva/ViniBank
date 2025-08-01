package com.example.serverdriveui.ui.validator.validators

import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.util.JsonUtil.getAsInt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MinLengthValidator(
    private val model: ValidatorModel,
    private val componentStateManager: ComponentStateManager,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main),
) : Validator {

    val states: MutableMap<String, Boolean> = mutableMapOf()
    val minLength: Int = model.data.getAsInt("length")

    init {
        componentStateManager.registerState(model.id, false)
        model.required.forEach {
            states[it] = false
        }
    }

    override fun initialize() {
        model.required.forEach { reqId ->
            scope.launch {
                componentStateManager.getState<String>(reqId)?.collect { result ->
                    states[reqId] = result.length >= minLength
                    validate()
                }
            }
        }
    }

    override fun close() {
        scope.cancel()
    }

    private fun validate() {
        componentStateManager.updateState(
            model.id,
            states.values.contains(false).not()
        )
    }

    companion object {
        const val IDENTIFIER = "minLength"
    }
}