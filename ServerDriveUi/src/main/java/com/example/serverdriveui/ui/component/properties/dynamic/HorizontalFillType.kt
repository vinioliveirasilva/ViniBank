package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier

sealed class HorizontalFillType(val identifier: String, val modifier: Modifier) {
    object Max : HorizontalFillType("Max", Modifier.fillMaxWidth())
    object Half : HorizontalFillType("Half", Modifier.fillMaxWidth(.5f))
    object Quarter : HorizontalFillType("Quarter", Modifier.fillMaxWidth(.25f))
    object Wrap : HorizontalFillType("Wrap", Modifier.wrapContentWidth())

    companion object {
        fun String?.toHorizontalFillType(): HorizontalFillType = when (this) {
            Max.identifier -> Max
            Half.identifier -> Half
            Quarter.identifier -> Quarter
            else -> Wrap
        }
    }
}