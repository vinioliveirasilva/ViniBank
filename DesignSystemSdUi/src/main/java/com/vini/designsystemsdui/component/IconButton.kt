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
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WeightProperty
import com.vini.designsystemsdui.property.WidthProperty

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
    visibility: VisibilityProperty = VisibilityProperty(),
    actions: List<Action> = emptyList(),
    validators: List<Validator> = emptyList()
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
        visibility.build(),
    ),
    components = components,
    actions = actions,
    validators = validators,
)