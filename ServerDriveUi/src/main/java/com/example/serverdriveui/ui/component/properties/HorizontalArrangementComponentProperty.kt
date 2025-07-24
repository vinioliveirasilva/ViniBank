package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable

interface HorizontalArrangementComponentProperty {

    @Composable
    fun getHorizontalArrangement(): Arrangement.Horizontal
    fun setHorizontalArrangement(value: HorizontalArrangementOptions)
}