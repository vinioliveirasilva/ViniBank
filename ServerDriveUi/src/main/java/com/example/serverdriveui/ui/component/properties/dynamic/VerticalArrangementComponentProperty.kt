package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.foundation.layout.Arrangement
import kotlinx.coroutines.flow.StateFlow

interface VerticalArrangementComponentProperty {
    fun getVerticalArrangement(): StateFlow<Arrangement.Vertical>
    fun setVerticalArrangement(value: Arrangement.Vertical)
}