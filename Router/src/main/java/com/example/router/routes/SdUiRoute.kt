package com.example.router.routes

import android.content.Intent
import android.os.Bundle
import com.example.router.Route
import com.example.router.RouteData

class SdUiRoute(override val data: SdUiRouteData) : Route(data) {
    override val id = "SdUi.SdUiActivity"
}

sealed class SdUiRouteData private constructor(bundleAction: Bundle.() -> Unit = {}) :
    RouteData(bundleAction) {
    data class StartAsDefault(
        val flowId: String,
        val screenData: Map<String, String> = emptyMap()
    ) : SdUiRouteData(
        bundleAction = {
            putString(FLOW_ID, flowId)
            putStringArrayList(SCREEN_DATA_KEYS, ArrayList(screenData.keys))
            putStringArrayList(SCREEN_DATA_VALUES, ArrayList(screenData.values))
        }
    )

    private companion object {
        const val FLOW_ID = "flowId"
        const val SCREEN_DATA_KEYS = "screenDataKeys"
        const val SCREEN_DATA_VALUES = "screenDataValues"
    }

    class SdUiRouteDataParser(intent: Intent? = null) {
        val flowId = intent?.getStringExtra(FLOW_ID).orEmpty()
        val screenData = intent?.getStringArrayListExtra(SCREEN_DATA_KEYS).orEmpty()
            .zip(intent?.getStringArrayListExtra(SCREEN_DATA_VALUES).orEmpty()).toMap()
            .toJsonObject()

        private fun Map<String, String>.toJsonObject() =
            "{${map { """${it.key}":"${it.value}""" }.joinToString(",")}}"
    }
}

