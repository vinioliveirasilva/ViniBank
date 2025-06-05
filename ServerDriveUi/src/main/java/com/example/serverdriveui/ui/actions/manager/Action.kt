package com.example.serverdriveui.ui.actions.manager

import androidx.navigation.NavHostController

interface Action {
    fun execute(navController: NavHostController)
}