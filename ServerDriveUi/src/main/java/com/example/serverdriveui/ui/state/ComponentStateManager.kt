package com.example.serverdriveui.ui.state

import androidx.lifecycle.SavedStateHandle
import com.example.serverdriveui.ui.action.manager.ActionStateManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

interface InternalComponentStateManager {
    fun setupForError(doOnFinishError: () -> Unit = {})
}

interface SavableComponentStateManager {
    fun saveState(savedStateHandle: SavedStateHandle)
    fun loadState(savedStateHandle: SavedStateHandle)
}

class ComponentStateManager(
    private val actionStateManager: ActionStateManager,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) : AutoCloseable, InternalComponentStateManager, SavableComponentStateManager {
    private val states = mutableMapOf<String, MutableStateFlow<Any?>>()
    private var lastActionState: Map<String, Any?> = emptyMap()
    private var internalDoOnFinishError: (() -> Unit)? = null

    init {
        actionStateManager
            .getState()
            .map { actionStates ->
                lastActionState = actionStates
                updateStates(actionStates)
            }
            .launchIn(scope)
    }

    override fun setupForError(doOnFinishError: () -> Unit) {
        states.clear()
        internalDoOnFinishError = doOnFinishError
    }

    override fun saveState(savedStateHandle: SavedStateHandle) {
        states.forEach { (id, stateFlow) ->
            savedStateHandle[id] = stateFlow.value
        }
    }

    override fun loadState(savedStateHandle: SavedStateHandle) {
        val states = savedStateHandle.keys()
            .map { key -> key to savedStateHandle.get<Any>(key) }
            .associate { it.first to it.second }
            .toMutableMap()
            .apply { putAll(lastActionState) }

        updateStates(states)
    }

    private fun updateStates(states: Map<String, Any?>) {
        states.forEach { (id, value) ->
            updateState(id, value)
        }
    }

    fun <T> registerState(id: String, data: T) {
        when {
            states.containsKey(id) -> return
            else -> states[id] = MutableStateFlow(data)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getState(id: String): StateFlow<T>? {
        if (!states.containsKey(id)) registerState(id, null)
        return states[id] as? StateFlow<T>
    }

    fun <T> updateState(id: String, data: T) {
        finishError()
        if (states[id] == null) {
            registerState(id, data)
        } else {
            states[id]?.value = data
        }
    }

    private fun finishError() {
        internalDoOnFinishError?.invoke()
        internalDoOnFinishError = null
    }

    override fun close() {
        states.clear()
    }
}