package com.example.serverdriveui.ui.component.components.pager.properties

import kotlinx.coroutines.flow.StateFlow

interface CurrentPageComponentProperty {
    fun getCurrentPage(): StateFlow<Int>
    fun setCurrentPage(value: Int)
}