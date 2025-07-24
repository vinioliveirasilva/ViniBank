package com.example.serverdriveui.ui.action.actions

import com.example.serverdriveui.GlobalStateManager
import com.example.serverdriveui.ui.action.manager.Action
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.example.serverdriveui.util.JsonUtil.getAsMap
import kotlinx.serialization.json.JsonObject

//TODO adicionar possibilidade de limpar cache de forma dinamica
class InvalidateCacheAction(
    val data: JsonObject,
    private val globalStateManager: GlobalStateManager,
    private val componentStateManager: ComponentStateManager,
    //private val sdUiCache: SdUiRepository,
) : Action {
    override fun execute() {
        val toClean = data.getAsMap("screens")
            .mapValues { it.value.asString() }

//        toClean.forEach {
//            sdUiCache.remove(
//                flow = it.key,
//                stage = it.value
//            )
//        }
    }

    companion object {
        const val IDENTIFIER = "invalidateCache"
    }
}