package com.example.serverdriveui.ui.component.components

import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

open class BaseComponent(
    private val model: JsonObject,
    private val validatorParser: ValidatorParser,
) : Component {
    init {
        validatorParser.parse(model).forEach { validator -> validator.initialize() }
    }
}