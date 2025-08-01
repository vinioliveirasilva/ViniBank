package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.ComponentProperty.HeightProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalAlignmentProperty
import com.vini.designsystemsdui.ComponentProperty.HorizontalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.IsEnabledProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingHorizontalProperty
import com.vini.designsystemsdui.ComponentProperty.PaddingVerticalProperty
import com.vini.designsystemsdui.ComponentProperty.ShapeProperty
import com.vini.designsystemsdui.ComponentProperty.TextProperty
import com.vini.designsystemsdui.ComponentProperty.VerticalAlignmentProperty
import com.vini.designsystemsdui.ComponentProperty.VerticalFillTypeProperty
import com.vini.designsystemsdui.ComponentProperty.VisibilityProperty
import com.vini.designsystemsdui.ComponentProperty.WidthProperty
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.property.ShapeOptions

fun outlinedButton(
    text: TextProperty = TextProperty("OutlinedButtonText"),
    isEnabled: IsEnabledProperty = IsEnabledProperty(),
    shape: ShapeProperty = ShapeProperty(ShapeOptions.Circle),

    verticalAlignment: VerticalAlignmentProperty = VerticalAlignmentProperty(),
    horizontalAlignment: HorizontalAlignmentProperty = HorizontalAlignmentProperty(),
    horizontalFillType: HorizontalFillTypeProperty = HorizontalFillTypeProperty(),
    verticalFillTypeOption: VerticalFillTypeProperty = VerticalFillTypeProperty(),
    paddingVertical: PaddingVerticalProperty = PaddingVerticalProperty(),
    paddingHorizontal: PaddingHorizontalProperty = PaddingHorizontalProperty(),
    height: HeightProperty? = null,
    width: WidthProperty? = null,
    isVisibility: VisibilityProperty = VisibilityProperty(),
) = ComponentUtil.component(
    type = "outlinedButton",
    properties = listOfNotNull(
        text.build(),
        isEnabled.build(),
        shape.build(),

        verticalAlignment.build(),
        horizontalAlignment.build(),
        horizontalFillType.build(),
        verticalFillTypeOption.build(),
        paddingVertical.build(),
        paddingHorizontal.build(),
        height?.build(),
        width?.build(),
        isVisibility.build(),
    )
)