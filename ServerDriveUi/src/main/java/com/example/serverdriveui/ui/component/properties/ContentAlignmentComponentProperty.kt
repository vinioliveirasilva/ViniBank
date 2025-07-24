package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

interface ContentAlignmentComponentProperty {

    @Composable
    fun getContentAlignment(): Alignment
    fun setContentAlignment(value: AlignmentOptions)
}