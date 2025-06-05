package com.example.serverdriveui.ui.components.properties.static

import androidx.compose.ui.text.style.TextAlign

class TextAlignProperty(staticProperties: Map<String, String>) : TextAlignComponentProperty {
    override val textAlign: TextAlign = staticProperties["textAlign"].toTextAlign()

    private fun String?.toTextAlign(): TextAlign = when (this) {
        "Center" -> TextAlign.Center
        "Start" -> TextAlign.Start
        "End" -> TextAlign.End
        else -> TextAlign.Start
    }
}