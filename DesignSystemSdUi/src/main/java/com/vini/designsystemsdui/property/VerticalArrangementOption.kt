package com.vini.designsystemsdui.property

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp


sealed class VerticalArrangementOption(
    open val name: String,
    open val verticalArrangement: Arrangement.Vertical,
) {
    object Top : VerticalArrangementOption("Top", Arrangement.Top)
    object Bottom : VerticalArrangementOption("Bottom", Arrangement.Bottom)
    object Center : VerticalArrangementOption("Center", Arrangement.Center)
    object SpaceAround : VerticalArrangementOption("SpaceAround", Arrangement.SpaceAround)
    object SpaceBetween : VerticalArrangementOption("SpaceBetween", Arrangement.SpaceBetween)
    object SpaceEvenly : VerticalArrangementOption("SpaceEvenly", Arrangement.SpaceEvenly)

    class SpacedBy(
        space: Int
    ) : VerticalArrangementOption("SpacedBy$space", Arrangement.spacedBy(space = space.dp))
}

fun String?.toVerticalArrangement() =
    when (this) {
        VerticalArrangementOption.Top.name -> VerticalArrangementOption.Top
        VerticalArrangementOption.Bottom.name -> VerticalArrangementOption.Bottom
        VerticalArrangementOption.Center.name -> VerticalArrangementOption.Center
        VerticalArrangementOption.SpaceAround.name -> VerticalArrangementOption.SpaceAround
        VerticalArrangementOption.SpaceBetween.name -> VerticalArrangementOption.SpaceBetween
        VerticalArrangementOption.SpaceEvenly.name -> VerticalArrangementOption.SpaceEvenly
        else -> null
    } ?: when (true) {
        this?.contains("SpacedBy") -> {
            val x = 1
            parseSpacedBy()
        }
        else -> VerticalArrangementOption.Top
    }

private fun String.parseSpacedBy() : VerticalArrangementOption {


    return (split("SpacedBy").lastOrNull()?.toInt() ?: 0).let {
        VerticalArrangementOption.SpacedBy(space = it)
    }
}