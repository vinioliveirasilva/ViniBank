package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.property.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.VerticalFillTypeOption

fun iconButton(
    horizontalFillType: HorizontalFillTypeOption = HorizontalFillTypeOption.None,
    verticalFillTypeOption: VerticalFillTypeOption = VerticalFillTypeOption.None,
    paddingVertical: Int = 0,
    paddingHorizontal: Int = 0,
    height: Int? = null,
    width: Int? = null,
    isVisibility: Boolean = true,
    content: List<Component>,
) = ComponentUtil.component(
    type = "iconButton",
    properties = listOfNotNull(
        ComponentUtil.property("horizontalFillType", horizontalFillType.name),
        ComponentUtil.property("verticalFillType", verticalFillTypeOption.name),
        ComponentUtil.property("paddingVertical", paddingVertical),
        ComponentUtil.property("paddingHorizontal", paddingHorizontal),
        height?.let { ComponentUtil.property("height", it) },
        width?.let { ComponentUtil.property("width", it) },
        ComponentUtil.property("isVisible", isVisibility),
    ),
    components = content
)