package com.example.serverdriveui.ui.action.manager

import androidx.annotation.CallSuper
import androidx.navigation.NavHostController

interface Action {
    @CallSuper
    fun execute(navController: NavHostController) = execute()
    fun execute() = Unit
    fun initialize() = Unit
}