package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Action
import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WidthProperty

fun card(
    components: List<Component> = emptyList(),

    verticalAlignmentProperty: VerticalAlignmentProperty = VerticalAlignmentProperty(),
    horizontalAlignmentProperty: HorizontalAlignmentProperty = HorizontalAlignmentProperty(),
    horizontalFillTypeProperty: HorizontalFillTypeProperty = HorizontalFillTypeProperty(),
    verticalFillTypeProperty: VerticalFillTypeProperty = VerticalFillTypeProperty(),
    paddingVerticalProperty: PaddingVerticalProperty = PaddingVerticalProperty(),
    paddingHorizontalProperty: PaddingHorizontalProperty = PaddingHorizontalProperty(),
    heightProperty: HeightProperty? = null,
    widthProperty: WidthProperty? = null,
    isVisibilityProperty: VisibilityProperty = VisibilityProperty(),
    actions: List<Action> = emptyList(),
    validators: List<Validator> = emptyList()
): Component = ComponentUtil.component(
    "card",
    listOfNotNull(
        verticalAlignmentProperty.build(),
        horizontalAlignmentProperty.build(),
        horizontalFillTypeProperty.build(),
        verticalFillTypeProperty.build(),
        paddingVerticalProperty.build(),
        paddingHorizontalProperty.build(),
        heightProperty?.build(),
        widthProperty?.build(),
        isVisibilityProperty.build(),
    ),
    components = components,
    actions = actions,
    validators = validators,
)