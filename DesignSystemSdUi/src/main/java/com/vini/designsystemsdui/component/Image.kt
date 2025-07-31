package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.property.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.VerticalFillTypeOption

fun image(
    size: Int? = null,
    iconName: String? = null,
    drawableName: String? = null,

    horizontalFillType: HorizontalFillTypeOption = HorizontalFillTypeOption.None,
    verticalFillTypeOption: VerticalFillTypeOption = VerticalFillTypeOption.None,
    paddingVertical: Int = 0,
    paddingHorizontal: Int = 0,
    height: Int? = null,
    width: Int? = null,
    isVisibility: Boolean = true,
) = ComponentUtil.component(
    type = "image",
    properties = listOfNotNull(
        size?.let { ComponentUtil.property("size", it) },
        iconName?.let { ComponentUtil.property("icon", it) },
        drawableName?.let { ComponentUtil.property("iconDrawable", it) },

        ComponentUtil.property("horizontalFillType", horizontalFillType.name),
        ComponentUtil.property("verticalFillType", verticalFillTypeOption.name),
        ComponentUtil.property("paddingVertical", paddingVertical),
        ComponentUtil.property("paddingHorizontal", paddingHorizontal),
        height?.let { ComponentUtil.property("height", it) },
        width?.let { ComponentUtil.property("width", it) },
        ComponentUtil.property("isVisible", isVisibility),
    ),
)