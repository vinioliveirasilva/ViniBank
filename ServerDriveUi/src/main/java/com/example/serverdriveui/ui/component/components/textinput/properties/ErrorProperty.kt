package com.example.serverdriveui.ui.component.components.textinput.properties

class ErrorProperty(val modifiers: Map<String, String>) : ErrorComponentProperty {
    override val errorMessage: String = modifiers["errorMessage"].orEmpty()
    override var isError: Boolean = modifiers["isError"].toBoolean()
}