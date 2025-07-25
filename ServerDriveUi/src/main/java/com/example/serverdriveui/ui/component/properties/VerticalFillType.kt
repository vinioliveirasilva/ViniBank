package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier

sealed class VerticalFillType(val identifier: String, val modifier: Modifier) {
    object Max : VerticalFillType("Max", Modifier.Companion.fillMaxHeight())
    object Half : VerticalFillType("Half", Modifier.Companion.fillMaxHeight(.5f))
    object Quarter : VerticalFillType("Quarter", Modifier.Companion.fillMaxHeight(.25f))
    object Wrap : VerticalFillType("Wrap", Modifier.wrapContentHeight())
    object None : VerticalFillType("", Modifier)

    companion object {
        fun String?.toVerticalFillType(): VerticalFillType = when (this) {
            Max.identifier -> Max
            Half.identifier -> Half
            Quarter.identifier -> Quarter
            Wrap.identifier -> Wrap
            else -> None
        }
    }
}