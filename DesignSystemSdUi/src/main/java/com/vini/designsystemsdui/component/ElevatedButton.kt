package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Action
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IsEnabledProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.ShapeProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WidthProperty
import com.vini.designsystemsdui.property.options.ShapeOptions

fun elevatedButton(
    text: TextProperty = TextProperty("ElevatedButtonText"),
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
    visibility: VisibilityProperty = VisibilityProperty(),
    actions: List<Action> = emptyList(),
    validators: List<Validator> = emptyList()
) = ComponentUtil.component(
    type = "elevatedButton",
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
        visibility.build(),
    ),
    actions = actions,
    validators = validators,
)