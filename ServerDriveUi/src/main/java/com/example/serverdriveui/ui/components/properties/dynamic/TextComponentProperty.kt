package com.example.serverdriveui.ui.components.properties.dynamic

import kotlinx.coroutines.flow.StateFlow

interface TextComponentProperty {
    fun getText() : StateFlow<String>
    fun setText(text: String)
}