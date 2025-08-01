package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.ComponentProperty.DrawableNameProperty
import com.vini.designsystemsdui.ComponentProperty.HeightProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalAlignmentProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.IconNameProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingHorizontalProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingVerticalProperty
import com.vini.designsystemsdui.ComponentProperty.SizeProperty
import com.vini.designsystemsdui.ComponentProperty.VerticalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.VisibilityProperty
import com.vini.designsystemsdui.ComponentProperty.WidthProperty
import com.vini.designsystemsdui.ComponentUtil

fun icon(
    iconName: IconNameProperty? = null,
    drawableName: DrawableNameProperty? = null,
    size: SizeProperty? = null,

    horizontalAlignment: HorizontalAlignmentProperty = HorizontalAlignmentProperty(),
    horizontalFillType: HorizontalFillTypeProperty = HorizontalFillTypeProperty(),
    verticalFillTypeOption: VerticalFillTypeProperty = VerticalFillTypeProperty(),
    paddingVertical: PaddingVerticalProperty = PaddingVerticalProperty(),
    paddingHorizontal: PaddingHorizontalProperty = PaddingHorizontalProperty(),
    height: HeightProperty? = null,
    width: WidthProperty? = null,
    isVisibility: VisibilityProperty = VisibilityProperty(),
) = ComponentUtil.component(
    type = "icon",
    properties = listOfNotNull(
        iconName?.build(),
        drawableName?.build(),
        size?.build(),

        horizontalAlignment.build(),
        horizontalFillType.build(),
        verticalFillTypeOption.build(),
        paddingVertical.build(),
        paddingHorizontal.build(),
        height?.build(),
        width?.build(),
        isVisibility.build(),
    ),
)