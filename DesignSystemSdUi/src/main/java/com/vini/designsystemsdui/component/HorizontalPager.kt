package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Action
import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import com.vini.designsystemsdui.property.ContentPaddingProperty
import com.vini.designsystemsdui.property.CurrentPageProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VisibilityProperty

fun horizontalPager(
    components: List<Component> = emptyList(),
    currentPage: CurrentPageProperty = CurrentPageProperty(),
    contentPadding: ContentPaddingProperty = ContentPaddingProperty(),
    verticalAlignment: VerticalAlignmentProperty = VerticalAlignmentProperty(),
    visibility: VisibilityProperty = VisibilityProperty(),
    actions: List<Action> = emptyList(),
    validators: List<Validator> = emptyList(),
) = ComponentUtil.component(
    "horizontalPager",
    listOf(
        currentPage.build(),
        contentPadding.build(),
        verticalAlignment.build(),
        visibility.build(),
    ),
    components = components,
    actions = actions,
    validators = validators,
)
