package com.example.serverdriveui.ui.component.properties.dynamic

import kotlinx.coroutines.flow.StateFlow

interface LabelComponentProperty {
    fun getLabel() : StateFlow<String>
    fun setLabel(value: String)
}