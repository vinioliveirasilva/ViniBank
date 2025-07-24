package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable

interface ErrorMessageComponentProperty {

    @Composable
    fun getErrorMessage() : String
    fun setErrorMessage(message: String)
}