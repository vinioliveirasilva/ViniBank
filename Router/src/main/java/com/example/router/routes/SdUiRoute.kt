package com.example.router.routes

import android.content.Intent
import android.os.Bundle
import com.example.router.Route
import com.example.router.RouteData

class SdUiRoute(override val data: SdUiRouteData? = null) : Route(data) {
    override val id = "SdUi.SdUiActivity"
}

sealed class SdUiRouteData private constructor(bundleAction: Bundle.() -> Unit = {}) : RouteData(bundleAction) {
    data class StartAsDefault(val screenId: String) : SdUiRouteData(
        bundleAction = { putString("screenId", screenId) }
    )
}

class SdUiRouteDataParser(intent: Intent? = null) {
    val screenId = intent?.getStringExtra("screenId").orEmpty()
}