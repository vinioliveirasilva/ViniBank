package com.example.router.routes

import android.os.Bundle
import com.example.router.Route
import com.example.router.RouteData

class SignUpRoute(override val data: SignUpRouteData? = null) : Route(data) {
    override val id = "SignUp.SignUpActivity"
}

sealed class SignUpRouteData(bundleAction: Bundle.() -> Unit = {}) : RouteData(bundleAction) {
    data class StartAsDefault(val a: String) : SignUpRouteData(
        bundleAction = { putString("a", a) }
    )
}

