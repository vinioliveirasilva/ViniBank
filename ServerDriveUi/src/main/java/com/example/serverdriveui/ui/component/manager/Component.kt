package com.example.serverdriveui.ui.component.manager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

interface Component {

    @get:Composable
    val internalModifier: Modifier
        get() = Modifier

    @Composable
    fun getComponent(navController: NavHostController): @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Red)
                .padding(5.dp)
                .background(Color.White)
        ) {
            Text("Componente desconhecido")
        }
    }

    @Composable
    fun getComponentAsColumn(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        { getComponent(navController) }

    @Composable
    fun getComponentAsRow(navController: NavHostController): @Composable RowScope.() -> Unit =
        { getComponent(navController) }

    @Composable
    fun getComponentLazyItemScope(navController: NavHostController): @Composable (LazyItemScope.() -> Unit) =
        { getComponent(navController) }

    fun getComponentLazyListScope(navController: NavHostController): (LazyListScope.() -> Unit) =
        { item { getComponent(navController) } }
}

interface InternalComponent {
    @Composable
    fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        throw IllegalStateException("Component cannot be used as generic")
    }
}