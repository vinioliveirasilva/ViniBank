package com.example.serverdriveui.ui.component.components

import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

open class BaseComponent(
    private val model: JsonObject,
    private val validatorParser: ValidatorParser,
    private val componentStateManager: ComponentStateManager,
) : Component {
    init {
        validatorParser.parse(model, componentStateManager).forEach { validator -> validator.initialize() }
    }
}