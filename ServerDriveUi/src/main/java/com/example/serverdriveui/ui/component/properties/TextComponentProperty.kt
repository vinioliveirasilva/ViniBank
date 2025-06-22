package com.example.serverdriveui.ui.component.properties

import kotlinx.coroutines.flow.StateFlow

interface TextComponentProperty {
    fun getText() : StateFlow<String>
    fun setText(text: String)
}