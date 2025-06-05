package com.example.router

import android.os.Bundle

abstract class Route(open val data: RouteData?) {
    abstract val id: String
}

abstract class RouteData(val bundleAction: Bundle.() -> Unit)