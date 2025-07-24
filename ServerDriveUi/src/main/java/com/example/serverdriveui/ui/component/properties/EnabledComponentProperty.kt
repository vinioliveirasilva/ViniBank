package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable

interface EnabledComponentProperty {
    @Composable
    fun getEnabled() : Boolean
    fun setEnabled(value: Boolean)
}