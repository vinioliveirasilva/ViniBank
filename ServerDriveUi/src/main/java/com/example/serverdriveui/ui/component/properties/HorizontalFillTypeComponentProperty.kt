package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.StateFlow

interface HorizontalFillTypeComponentProperty {
    fun getHorizontalFillType(): StateFlow<HorizontalFillType>
    fun setHorizontalFillType(value: HorizontalFillType)
    val horizontalFillTypeModifier : Modifier
}