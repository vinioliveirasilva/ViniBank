package com.example.serverdriveui.ui.component.components.bottomnavigation.properties

import kotlinx.coroutines.flow.StateFlow

interface BottomNavigationDestinationIndexComponent {
    fun getIndex(): StateFlow<Int>
}