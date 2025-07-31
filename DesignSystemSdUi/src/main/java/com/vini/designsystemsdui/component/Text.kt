package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.property.FontWeightOption
import com.vini.designsystemsdui.property.HorizontalFillTypeOption
import com.vini.designsystemsdui.property.TextAlignOption
import com.vini.designsystemsdui.property.VerticalFillTypeOption

fun text(
    text: String = "Text",
    textAlign: TextAlignOption = TextAlignOption.Start,
    fontSize: Int = 14,
    fontWeight: FontWeightOption = FontWeightOption.Default,

    horizontalFillType: HorizontalFillTypeOption = HorizontalFillTypeOption.None,
    verticalFillTypeOption: VerticalFillTypeOption = VerticalFillTypeOption.None,
    paddingVertical: Int = 0,
    paddingHorizontal: Int = 0,
    height: Int? = null,
    width: Int? = null,
    isVisibility: Boolean = true,
) = ComponentUtil.component(
    "text",
    listOfNotNull(
        ComponentUtil.property("text", text),
        ComponentUtil.property("textAlign", textAlign.name),
        ComponentUtil.property("fontSize", fontSize),
        ComponentUtil.property("fontWeight", fontWeight.name),

        ComponentUtil.property("horizontalFillType", horizontalFillType.name),
        ComponentUtil.property("verticalFillType", verticalFillTypeOption.name),
        ComponentUtil.property("paddingVertical", paddingVertical),
        ComponentUtil.property("paddingHorizontal", paddingHorizontal),
        height?.let { ComponentUtil.property("height", it) },
        width?.let { ComponentUtil.property("width", it) },
        ComponentUtil.property("isVisible", isVisibility),
    )
)