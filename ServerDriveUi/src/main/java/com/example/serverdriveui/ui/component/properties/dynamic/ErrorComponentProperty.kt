package com.example.serverdriveui.ui.component.properties.dynamic

import kotlinx.coroutines.flow.StateFlow

interface ErrorComponentProperty {
    fun getIsError() : StateFlow<Boolean>
    fun setIsError(value: Boolean)
}