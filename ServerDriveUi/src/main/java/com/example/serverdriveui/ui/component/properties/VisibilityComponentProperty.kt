package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable

interface VisibilityComponentProperty {
    @Composable
    fun getIsVisible(): Boolean
    fun setIsVisible(isVisible: Boolean)
}