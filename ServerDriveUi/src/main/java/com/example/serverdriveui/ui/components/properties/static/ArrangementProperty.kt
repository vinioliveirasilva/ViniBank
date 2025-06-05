package com.example.serverdriveui.ui.components.properties.static

import androidx.compose.foundation.layout.Arrangement

class ArrangementProperty(modifiers: Map<String, String>) : ArrangementComponentProperty {
    override val verticalArrangement: Arrangement.Vertical = modifiers["verticalArrangement"].toVerticalArrangement()
    override val horizontalArrangement: Arrangement.Horizontal = modifiers["horizontalArrangement"].toHorizontalArrangement()


    private fun String?.toVerticalArrangement() = when(this) {
        "Top" -> Arrangement.Top
        "Bottom" -> Arrangement.Bottom
        "Center" -> Arrangement.Center
        "SpaceAround" -> Arrangement.SpaceAround
        "SpaceBetween" -> Arrangement.SpaceBetween
        "SpaceEvenly" -> Arrangement.SpaceEvenly
        else -> Arrangement.Top
    }

    private fun String?.toHorizontalArrangement() = when(this) {
        "Start" -> Arrangement.Start
        "End" -> Arrangement.End
        "Center" -> Arrangement.Center
        "SpaceBetween" -> Arrangement.SpaceBetween
        "SpaceAround" -> Arrangement.SpaceAround
        else -> Arrangement.Start
    }

}