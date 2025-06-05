package com.example.serverdriveui.ui.components.properties.dynamic

import kotlinx.coroutines.flow.StateFlow

interface EnabledComponentProperty {
    fun getEnabled() : StateFlow<Boolean>
    fun setEnabled(value: Boolean)
}