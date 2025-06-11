package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.foundation.layout.Arrangement
import kotlinx.coroutines.flow.StateFlow

interface HorizontalArrangementComponentProperty {
    fun getHorizontalArrangement(): StateFlow<Arrangement.Horizontal>
    fun setHorizontalArrangement(value: Arrangement.Horizontal)
}