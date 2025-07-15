package com.example.serverdriveui.ui.action.manager

import androidx.navigation.NavHostController

interface Action : AutoCloseable {
    fun execute(navController: NavHostController)
    override fun close() = Unit
}