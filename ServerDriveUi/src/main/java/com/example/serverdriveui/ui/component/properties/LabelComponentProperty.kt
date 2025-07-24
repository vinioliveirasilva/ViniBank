package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable

interface LabelComponentProperty {

    @Composable
    fun getLabel() : String
    fun setLabel(value: String)
}