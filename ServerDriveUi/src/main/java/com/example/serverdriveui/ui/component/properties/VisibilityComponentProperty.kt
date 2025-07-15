package com.example.serverdriveui.ui.component.properties

import kotlinx.coroutines.flow.StateFlow

interface VisibilityComponentProperty {
    fun getIsVisible(): StateFlow<Boolean>
    fun setIsVisible(isVisible: Boolean)
}