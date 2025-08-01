package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.VerticalArrangementOption

class VerticalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VerticalArrangementComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalArrangement",
        transformToData = { it?.asString() },
        defaultPropertyValue = VerticalArrangementOption.Top.name,
    ) {

    @Composable
    override fun getVerticalArrangement() =
        getValue().toVerticalArrangement().toArrangement()

    override fun setVerticalArrangement(value: VerticalArrangementOption) = setValue(value.name)

    private fun VerticalArrangementOption.toArrangement() = when (this) {
        VerticalArrangementOption.Top -> Arrangement.Top
        VerticalArrangementOption.Bottom -> Arrangement.Bottom
        VerticalArrangementOption.Center -> Arrangement.Center
        VerticalArrangementOption.SpaceAround -> Arrangement.SpaceAround
        VerticalArrangementOption.SpaceBetween -> Arrangement.SpaceBetween
        VerticalArrangementOption.SpaceEvenly -> Arrangement.SpaceEvenly
        is VerticalArrangementOption.SpacedBy -> Arrangement.spacedBy(space = space.dp)
    }


    //TODO
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
            this?.contains("SpacedBy") -> parseSpacedBy()
            else -> VerticalArrangementOption.Top
        }

    private fun String.parseSpacedBy() : VerticalArrangementOption {


        return (split("SpacedBy").lastOrNull()?.toInt() ?: 0).let {
            VerticalArrangementOption.SpacedBy(space = it)
        }
    }
}

interface VerticalArrangementComponentProperty {

    @Composable
    fun getVerticalArrangement(): Arrangement.Vertical
    fun setVerticalArrangement(value: VerticalArrangementOption)
}