package com.example.serverdriveui.ui.component.components.navigationbar.properties

import kotlinx.coroutines.flow.StateFlow

interface NavigationDestinationIndexComponent {
    fun getIndex(): StateFlow<Int>
}