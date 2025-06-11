package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.flow.StateFlow

interface VisualTransformationComponentProperty {
    fun getVisualTransformation(): StateFlow<VisualTransformation>
    fun setVisualTransformation(visualTransformation: VisualTransformation)
}

