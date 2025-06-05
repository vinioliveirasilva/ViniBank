package com.example.serverdriveui.ui.components.properties.static

class LabelProperty(val modifiers: Map<String, String>) : LabelComponentProperty {
    override val label: String = modifiers["label"].orEmpty()
}