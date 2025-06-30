package com.example.serverdriveui.ui.component.manager

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

interface Component {

    @get:Composable
    val internalModifier: Modifier
    @Composable
    fun getComponent(navController: NavHostController): @Composable () -> Unit = {}
    @Composable
    fun getComponentAsColumn(navController: NavHostController): @Composable ColumnScope.() -> Unit = {}
    @Composable
    fun getComponentAsRow(navController: NavHostController): @Composable RowScope.() -> Unit = {}
    @Composable
    fun getComponentLazyItemScope(navController: NavHostController): @Composable (LazyItemScope.() -> Unit) = {}
    fun getComponentLazyListScope(navController: NavHostController): (LazyListScope.() -> Unit) = {}
}

interface InternalComponent {
    @Composable
    fun getInternalComponent(navController: NavHostController, modifier: Modifier): @Composable () -> Unit = {
        throw IllegalStateException("Component cannot be used as generic")
    }
}