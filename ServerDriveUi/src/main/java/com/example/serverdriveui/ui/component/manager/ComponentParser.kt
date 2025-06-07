package com.example.serverdriveui.ui.component.manager

import com.example.serverdriveui.SdUiJsonParseExtension
import com.example.serverdriveui.service.model.ComponentModel
import com.example.serverdriveui.ui.actions.manager.ActionParser
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonArray

class ComponentParser(
    private val componentManager: ComponentManager,
    private val actionParser: ActionParser,
    private val validatorParser: ValidatorParser
) {
    fun parse(data: JsonArray): List<Component> {
        return data.asJsonArray.map {
            val (type, dataObject) = SdUiJsonParseExtension.parseBaseData(it.asJsonObject)
            componentManager.getComponent(type, dataObject)
        }
    }

    fun parse(componentsModel: List<ComponentModel>): List<Component> {
        return componentsModel.map {
            componentManager.getComponent(
                it.type,
                it.dynamicProperty,
                it.staticProperty,
                parse(it.components),
                actionParser.parse(it.action),
                it.validators.map { validatorParser.parse(it) }
            )
        }
    }
}