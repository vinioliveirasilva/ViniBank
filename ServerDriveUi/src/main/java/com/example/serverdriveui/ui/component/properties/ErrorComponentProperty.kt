package com.example.serverdriveui.ui.component.properties

import kotlinx.coroutines.flow.StateFlow

interface ErrorComponentProperty {
    fun getIsError() : StateFlow<Boolean>
    fun setIsError(value: Boolean)
}