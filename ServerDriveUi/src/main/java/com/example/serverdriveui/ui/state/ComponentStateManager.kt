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
    fun clean()
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

    init {
        actionStateManager
            .getState()
            .map { actionStates ->
                actionStates.forEach { (id, action) -> updateState(id, action) }
            }
            .launchIn(scope)
    }

    override fun clean() {
        states.clear()
    }

    override fun saveState(savedStateHandle: SavedStateHandle) {
        states.forEach { (id, stateFlow) ->
            savedStateHandle[id] = stateFlow.value
        }
    }

    override fun loadState(savedStateHandle: SavedStateHandle) {
        savedStateHandle.keys().forEach {
            registerState<Any?>(it, savedStateHandle[it])
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
        if (states[id] == null) {
            registerState(id, data)
        } else {
            states[id]?.value = data
        }
    }

    override fun close() {
        states.clear()
    }
}