package com.example.serverdriveui.ui.action.manager

import androidx.navigation.NavHostController

interface Action {
    fun execute(navController: NavHostController)
}