package com.example.serverdriveui.ui.component.properties

import kotlinx.coroutines.flow.StateFlow

interface LabelComponentProperty {
    fun getLabel() : StateFlow<String>
    fun setLabel(value: String)
}