package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString

class VerticalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VerticalArrangementComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalArrangement",
        transformToData = { it?.asString() },
        defaultPropertyValue = VerticalArrangementOption.Top.id,
    ) {

    @Composable
    override fun getVerticalArrangement() =
        getValue().toVerticalArrangement().verticalArrangement

    override fun setVerticalArrangement(value: VerticalArrangementOption) = setValue(value.id)
}

sealed class VerticalArrangementOption(
    open val id: String,
    open val verticalArrangement: Arrangement.Vertical,
) {
    object Top : VerticalArrangementOption("Top", Arrangement.Top)
    object Bottom : VerticalArrangementOption("Bottom", Arrangement.Bottom)
    object Center : VerticalArrangementOption("Center", Arrangement.Center)
    object SpaceAround : VerticalArrangementOption("SpaceAround", Arrangement.SpaceAround)
    object SpaceBetween : VerticalArrangementOption("SpaceBetween", Arrangement.SpaceBetween)
    object SpaceEvenly : VerticalArrangementOption("SpaceEvenly", Arrangement.SpaceEvenly)

    class SpaceBy(
        override val id: String,
        override val verticalArrangement: Arrangement.Vertical,
    ) : VerticalArrangementOption(id, verticalArrangement)
}

private fun String?.toVerticalArrangement() =
    when (this) {
        VerticalArrangementOption.Top.id -> VerticalArrangementOption.Top
        VerticalArrangementOption.Bottom.id -> VerticalArrangementOption.Bottom
        VerticalArrangementOption.Center.id -> VerticalArrangementOption.Center
        VerticalArrangementOption.SpaceAround.id -> VerticalArrangementOption.SpaceAround
        VerticalArrangementOption.SpaceBetween.id -> VerticalArrangementOption.SpaceBetween
        VerticalArrangementOption.SpaceEvenly.id -> VerticalArrangementOption.SpaceEvenly
        else -> null
    } ?: when (true) {
        this?.contains("SpacedBy") -> parseSpacedBy()
        else -> VerticalArrangementOption.Top
    }

private fun String.parseSpacedBy() = (split("SpacedBy").lastOrNull()?.toInt() ?: 0).let {
    VerticalArrangementOption.SpaceBy(
        id = "SpacedBy$it",
        verticalArrangement = Arrangement.spacedBy(space = it.dp)
    )
}