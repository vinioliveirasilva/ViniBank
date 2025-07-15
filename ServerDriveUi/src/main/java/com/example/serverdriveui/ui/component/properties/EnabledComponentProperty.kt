package com.example.serverdriveui.ui.component.properties

import kotlinx.coroutines.flow.StateFlow

interface EnabledComponentProperty {
    fun getEnabled() : StateFlow<Boolean>
    fun setEnabled(value: Boolean)
}