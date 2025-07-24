package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.VisualTransformation

interface VisualTransformationComponentProperty {
    @Composable
    fun getVisualTransformation(): VisualTransformation
    fun setVisualTransformation(visualTransformation: VisualTransformationOption)
}

