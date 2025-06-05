package com.example.serverdriveui.ui.components.properties.static

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier
import com.example.serverdriveui.ui.components.properties.static.FillTypeModifier.HorizontalFillType.Companion.toHorizontalFillType
import com.example.serverdriveui.ui.components.properties.static.FillTypeModifier.VerticalFillType.Companion.toVerticalFillType

data class FillTypeModifier(
    private val modifiers: Map<String, String>
) : FillTypeComponentModifier {

    private val horizontalFillType =
        modifiers["horizontalFillType"]?.toHorizontalFillType()?.modifier ?: Modifier
    private val verticalFillType =
        modifiers["verticalFillType"]?.toVerticalFillType()?.modifier ?: Modifier

    override val fillTypeModifier = Modifier
        .then(horizontalFillType)
        .then(verticalFillType)

    private sealed class HorizontalFillType(val identifier: String, val modifier: Modifier) {
        object Max : HorizontalFillType("Max", Modifier.fillMaxWidth())
        object Half : HorizontalFillType("Half", Modifier.fillMaxWidth(.5f))
        object Quarter : HorizontalFillType("Quarter", Modifier.fillMaxWidth(.25f))
        object Wrap : HorizontalFillType("Wrap", Modifier.wrapContentWidth())

        companion object {
            fun String?.toHorizontalFillType(): HorizontalFillType = when (this) {
                Max.identifier -> Max
                Half.identifier -> Half
                Quarter.identifier -> Quarter
                Wrap.identifier -> Wrap
                else -> throw IllegalStateException("Unknown horizontal fill type")
            }
        }
    }

    private sealed class VerticalFillType(val identifier: String, val modifier: Modifier) {
        object Max : VerticalFillType("Max", Modifier.fillMaxHeight())
        object Half : VerticalFillType("Half", Modifier.fillMaxHeight(.5f))
        object Quarter : VerticalFillType("Quarter", Modifier.fillMaxHeight(.25f))
        object Wrap : VerticalFillType("Wrap", Modifier.wrapContentHeight())

        companion object {
            fun String?.toVerticalFillType(): VerticalFillType = when (this) {
                Max.identifier -> Max
                Half.identifier -> Half
                Quarter.identifier -> Quarter
                Wrap.identifier -> Wrap
                else -> throw IllegalStateException("Unknown vertical fill type")
            }
        }
    }
}