package com.example.serverdriveui.ui.component.components.pager.properties

import androidx.compose.runtime.Composable

interface CurrentPageComponentProperty {

    @Composable
    fun getCurrentPage(): Int
    fun setCurrentPage(value: Int)
}