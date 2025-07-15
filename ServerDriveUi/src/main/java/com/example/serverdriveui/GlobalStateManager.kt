package com.example.serverdriveui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class GlobalStateManager(
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : AutoCloseable {
    private val globalState = mutableMapOf<String, MutableSharedFlow<Any?>>()

    fun registerState(id: String) {
        if (globalState.containsKey(id)) return
        globalState[id] = MutableSharedFlow()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getState(id: String): SharedFlow<T>? {
        return globalState[id] as? SharedFlow<T>
    }

    fun <T> updateState(id: String, data: T) = scope.launch {
        globalState[id]?.emit(data)
    }

    override fun close() {
        scope.cancel()
        globalState.clear()
    }
}