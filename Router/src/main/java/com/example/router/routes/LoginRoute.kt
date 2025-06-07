package com.example.router.routes

import com.example.router.Route
import com.example.router.RouteData

class LoginRoute(override val data: RouteData? = null) : Route(data) {
    override val id = "Login.LoginActivity"
}