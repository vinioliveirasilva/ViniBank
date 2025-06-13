package com.example.serverdriveui.ui.validator

import android.util.Patterns
import com.example.serverdriveui.service.model.ValidatorModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class EmailValidator(
    private val model: ValidatorModel,
    private val componentStateManager: ComponentStateManager,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main),
) : Validator {

    val states: MutableMap<String, Boolean> = mutableMapOf()

    init {
        componentStateManager.registerState<Boolean>(model.id, false)
        model.required.forEach {
            states[it] = false
        }
    }

    override fun initialize() {
        model.required.forEach { reqId ->
            scope.launch {
                componentStateManager.getState<String>(reqId)?.collect { result ->
                    states[reqId] = Patterns.EMAIL_ADDRESS.matcher(result).matches()
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
        const val IDENTIFIER = "emailValid"
    }
}