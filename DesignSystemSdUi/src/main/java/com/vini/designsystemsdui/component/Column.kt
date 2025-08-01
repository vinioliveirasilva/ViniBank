package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentProperty.HeightProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalAlignmentProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingHorizontalProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingVerticalProperty
import com.vini.designsystemsdui.ComponentProperty.VerticalAlignmentProperty
import com.vini.designsystemsdui.ComponentProperty.VerticalArrangementProperty
import com.vini.designsystemsdui.ComponentProperty.VerticalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.VisibilityProperty
import com.vini.designsystemsdui.ComponentProperty.WeightProperty
import com.vini.designsystemsdui.ComponentProperty.WidthProperty
import com.vini.designsystemsdui.ComponentUtil

fun column(
    components: List<Component> = emptyList(),

    verticalArrangement: VerticalArrangementProperty = VerticalArrangementProperty(),
    horizontalAlignment: HorizontalAlignmentProperty = HorizontalAlignmentProperty(),
    verticalAlignment: VerticalAlignmentProperty = VerticalAlignmentProperty(),
    horizontalFillType: HorizontalFillTypeProperty = HorizontalFillTypeProperty(),
    verticalFillType: VerticalFillTypeProperty = VerticalFillTypeProperty(),
    paddingVertical: PaddingVerticalProperty = PaddingVerticalProperty(),
    paddingHorizontal: PaddingHorizontalProperty = PaddingHorizontalProperty(),
    height: HeightProperty? = null,
    width: WidthProperty? = null,
    weight: WeightProperty? = null,
    isVisibility: VisibilityProperty = VisibilityProperty(),
) = ComponentUtil.component(
    "column",
    listOfNotNull(
        verticalArrangement.build(),
        horizontalAlignment.build(),
        verticalAlignment.build(),
        horizontalFillType.build(),
        verticalFillType.build(),
        paddingVertical.build(),
        paddingHorizontal.build(),
        height?.build(),
        width?.build(),
        weight?.build(),
        isVisibility.build(),
    ),
    components = components,
)