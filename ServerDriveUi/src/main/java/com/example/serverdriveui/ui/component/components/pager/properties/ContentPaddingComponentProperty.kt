package com.example.serverdriveui.ui.component.components.pager.properties

import kotlinx.coroutines.flow.StateFlow

interface ContentPaddingComponentProperty {
    fun getContentPadding(): StateFlow<Int>
    fun setContentPadding(value: Int)
}