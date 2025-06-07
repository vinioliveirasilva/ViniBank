package com.example.serverdriveui.ui.component.components.createpassword.properties

import kotlinx.coroutines.flow.StateFlow

interface ValidPasswordComponentProperty {
    fun isPasswordValid() : StateFlow<Boolean>
    fun setPasswordValid(isValid: Boolean)
}