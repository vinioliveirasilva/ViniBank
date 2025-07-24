package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable

interface ErrorComponentProperty {

    @Composable
    fun getIsError() : Boolean
    fun setIsError(value: Boolean)
}