package com.example.serverdriveui.ui.component.properties.static

import androidx.compose.ui.Alignment

class AlignmentProperty(modifiers: Map<String, String>) : AlignmentComponentProperty {
    override val verticalAlignment: Alignment.Vertical = modifiers["verticalAlignment"].toVerticalAlignment()
    override val horizontalAlignment: Alignment.Horizontal = modifiers["horizontalAlignment"].toHorizontalAlignment()

    private fun String?.toVerticalAlignment(): Alignment.Vertical = when (this) {
        "Center" -> Alignment.CenterVertically
        "Bottom" -> Alignment.Bottom
        "Top" -> Alignment.Top
        else -> Alignment.Top
    }

    private fun String?.toHorizontalAlignment() : Alignment.Horizontal = when(this) {
        "Center" -> Alignment.CenterHorizontally
        "Start" -> Alignment.Start
        "End" -> Alignment.End
        else -> Alignment.Start
    }
}