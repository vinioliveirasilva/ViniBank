package com.example.serverdriveui.ui.component.properties

import kotlinx.coroutines.flow.StateFlow

interface ErrorMessageComponentProperty {
    fun getErrorMessage() : StateFlow<String>
    fun setErrorMessage(message: String)
}