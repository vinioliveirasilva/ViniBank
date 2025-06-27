package com.example.serverdriveui.ui.component.components.bottomnavigation.properties

import kotlinx.coroutines.flow.StateFlow

interface BottomNavigationDestinationComponent {
    fun getSelectedDestination(): StateFlow<Int>
    fun setSelectedDestination(index: Int)
}