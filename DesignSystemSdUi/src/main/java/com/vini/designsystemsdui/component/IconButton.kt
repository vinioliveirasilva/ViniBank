package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentProperty.HeightProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalAlignmentProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingHorizontalProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingVerticalProperty
import com.vini.designsystemsdui.ComponentProperty.VerticalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.VisibilityProperty
import com.vini.designsystemsdui.ComponentProperty.WeightProperty
import com.vini.designsystemsdui.ComponentProperty.WidthProperty
import com.vini.designsystemsdui.ComponentUtil

fun iconButton(
    components: List<Component>,
    horizontalAlignment: HorizontalAlignmentProperty = HorizontalAlignmentProperty(),
    horizontalFillType: HorizontalFillTypeProperty = HorizontalFillTypeProperty(),
    verticalFillTypeOption: VerticalFillTypeProperty = VerticalFillTypeProperty(),
    paddingVertical: PaddingVerticalProperty = PaddingVerticalProperty(),
    paddingHorizontal: PaddingHorizontalProperty = PaddingHorizontalProperty(),
    height: HeightProperty? = null,
    width: WidthProperty? = null,
    weight: WeightProperty? = null,
    isVisibility: VisibilityProperty = VisibilityProperty(),
) = ComponentUtil.component(
    type = "iconButton",
    properties = listOfNotNull(
        horizontalAlignment.build(),
        horizontalFillType.build(),
        verticalFillTypeOption.build(),
        paddingVertical.build(),
        paddingHorizontal.build(),
        height?.build(),
        width?.build(),
        weight?.build(),
        isVisibility.build(),
    ),
    components = components
)