package com.example.serverdriveui.ui.state

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComponentStateManager() : AutoCloseable {

    //TODO Corrigir gambiarra
    var shouldUpdate = false
    val updatedStates = mutableListOf<String>()
    private val states = mutableMapOf<String, MutableStateFlow<Any?>>()

    fun <T> registerState(id: String, data: T) {
        when {
            shouldUpdate && states.containsKey(id) && updatedStates.contains(id).not() -> {
                updateState(id, data)
                updatedStates.add(id)
            }

            states.containsKey(id) -> return
            else -> states[id] = MutableStateFlow(data)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getState(id: String): StateFlow<T>? {
        return states[id] as? StateFlow<T>
    }

    fun <T> updateState(id: String, data: T) {
        states[id]?.value = data
    }

    override fun close() {
        states.clear()
    }
}