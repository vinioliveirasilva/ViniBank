package com.example.serverdriveui.ui.component.manager

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

interface Component {
    @Composable
    fun getComponent(navController: NavHostController): @Composable LazyListScope.() -> Unit = {}
}