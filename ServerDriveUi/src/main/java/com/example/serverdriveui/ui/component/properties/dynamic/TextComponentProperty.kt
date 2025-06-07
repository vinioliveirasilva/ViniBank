package com.example.serverdriveui.ui.component.properties.dynamic

import kotlinx.coroutines.flow.StateFlow

interface TextComponentProperty {
    fun getText() : StateFlow<String>
    fun setText(text: String)
}