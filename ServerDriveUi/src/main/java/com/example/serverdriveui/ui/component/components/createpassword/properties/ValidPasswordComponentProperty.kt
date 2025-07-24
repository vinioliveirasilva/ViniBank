package com.example.serverdriveui.ui.component.components.createpassword.properties

import androidx.compose.runtime.Composable

interface ValidPasswordComponentProperty {

    @Composable
    fun isPasswordValid() : Boolean
    fun setPasswordValid(isValid: Boolean)
}