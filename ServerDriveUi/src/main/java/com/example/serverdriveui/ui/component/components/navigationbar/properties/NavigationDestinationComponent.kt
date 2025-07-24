package com.example.serverdriveui.ui.component.components.navigationbar.properties

import androidx.compose.runtime.Composable

interface NavigationDestinationComponent {

    @Composable
    fun getSelectedDestination(): Int
    fun setSelectedDestination(index: Int)
}