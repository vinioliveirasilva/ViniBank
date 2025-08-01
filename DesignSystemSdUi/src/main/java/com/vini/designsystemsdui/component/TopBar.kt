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

fun topBar(
    components: List<Component> = emptyList(),
    navigationIcons: List<Component> = emptyList(),
    actionIcons: List<Component> = emptyList(),

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
    "topAppBar",
    listOfNotNull(
        verticalAlignment.build(),
        horizontalAlignment.build(),
        horizontalFillType.build(),
        verticalFillTypeOption.build(),
        paddingVertical.build(),
        paddingHorizontal.build(),
        height?.build(),
        width?.build(),
        isVisibility.build(),
    ),
    components = components,
    customComponents = arrayOf(
        "navigationIcons" to navigationIcons,
        "actionIcons" to actionIcons
    )
)