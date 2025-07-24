package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable

interface VerticalArrangementComponentProperty {

    @Composable
    fun getVerticalArrangement(): Arrangement.Vertical
    fun setVerticalArrangement(value: VerticalArrangementOption)
}