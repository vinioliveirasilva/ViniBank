package com.example.serverdriveui.ui.component.components.navigationbar.properties

import kotlinx.coroutines.flow.StateFlow

interface NavigationDestinationComponent {
    fun getSelectedDestination(): StateFlow<Int>
    fun setSelectedDestination(index: Int)
}