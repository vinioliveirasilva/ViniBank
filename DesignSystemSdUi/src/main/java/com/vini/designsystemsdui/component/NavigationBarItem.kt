package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Action
import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import com.vini.designsystemsdui.property.DestinationIndexProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.SelectedNavigationDestinationIndexProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WidthProperty

fun navigationBarItem(
    selectedDestinationIndex: SelectedNavigationDestinationIndexProperty = SelectedNavigationDestinationIndexProperty(),
    destinationIndex: DestinationIndexProperty = DestinationIndexProperty(),
    components: List<Component> = emptyList(),
    selectedIcon: List<Component> = emptyList(),
    unselectedIcon: List<Component> = emptyList(),

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
    "navigationBarItem",
    listOfNotNull(
        selectedDestinationIndex.build(),
        destinationIndex.build(),

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
    components = components,
    customComponents = arrayOf(
        "selectedIcon" to selectedIcon,
        "unselectedIcon" to unselectedIcon,
    ),
    actions = actions,
    validators = validators,
)