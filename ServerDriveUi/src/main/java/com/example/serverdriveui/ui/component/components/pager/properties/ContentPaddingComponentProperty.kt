package com.example.serverdriveui.ui.component.components.pager.properties

import androidx.compose.runtime.Composable

interface ContentPaddingComponentProperty {

    @Composable
    fun getContentPadding(): Int
    fun setContentPadding(value: Int)
}