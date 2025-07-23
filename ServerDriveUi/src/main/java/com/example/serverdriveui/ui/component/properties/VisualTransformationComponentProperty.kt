package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.flow.StateFlow

interface VisualTransformationComponentProperty {
    fun getVisualTransformation(): StateFlow<VisualTransformation>
    fun setVisualTransformation(visualTransformation: VisualTransformationOption)
}

