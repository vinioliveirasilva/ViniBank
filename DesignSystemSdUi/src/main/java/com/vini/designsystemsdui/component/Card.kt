package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentProperty.HeightProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalAlignmentProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingHorizontalProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingVerticalProperty
import com.vini.designsystemsdui.ComponentProperty.VerticalAlignmentProperty
import com.vini.designsystemsdui.ComponentProperty.VerticalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.VisibilityProperty
import com.vini.designsystemsdui.ComponentProperty.WidthProperty
import com.vini.designsystemsdui.ComponentUtil

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
    components = components
)