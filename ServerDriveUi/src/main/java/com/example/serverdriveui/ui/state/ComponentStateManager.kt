package com.example.serverdriveui.ui.state

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComponentStateManager(
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : AutoCloseable {
    private val states = mutableMapOf<String, MutableStateFlow<Any?>>()

    fun <T> registerState(id: String, data: T) {
        if (states.containsKey(id)) return
        states[id] = MutableStateFlow(data)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getState(id: String) : StateFlow<T>? {
        return states[id] as? StateFlow<T>
    }

    fun <T> updateState(id: String, data: T) {
        states[id]?.value = data
    }

    override fun close() {
        states.clear()
    }
}