package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Action
import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import com.vini.designsystemsdui.property.ErrorMessageProperty
import com.vini.designsystemsdui.property.ErrorProperty
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.HorizontalAlignmentProperty
import com.vini.designsystemsdui.property.HorizontalFillTypeProperty
import com.vini.designsystemsdui.property.IsEnabledProperty
import com.vini.designsystemsdui.property.KeyboardOptionsProperty
import com.vini.designsystemsdui.property.LabelProperty
import com.vini.designsystemsdui.property.PaddingHorizontalProperty
import com.vini.designsystemsdui.property.PaddingVerticalProperty
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VerticalAlignmentProperty
import com.vini.designsystemsdui.property.VerticalFillTypeProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.VisualTransformationProperty
import com.vini.designsystemsdui.property.WidthProperty

fun outlinedTextInput(
    text: TextProperty = TextProperty(""),
    label: LabelProperty = LabelProperty(""),
    isEnabled: IsEnabledProperty = IsEnabledProperty(),
    visualTransformation: VisualTransformationProperty = VisualTransformationProperty(),
    keyboardOptions: KeyboardOptionsProperty = KeyboardOptionsProperty(),
    error: ErrorProperty = ErrorProperty(),
    errorMessage: ErrorMessageProperty = ErrorMessageProperty(),

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
    validators: List<Validator> = emptyList(),
    trailingIcon: List<Component> = emptyList()
) = ComponentUtil.component(
    type = "outlinedTextInput",
    properties = listOfNotNull(
        text.build(),
        isEnabled.build(),
        label.build(),
        visualTransformation.build(),
        keyboardOptions.build(),
        error.build(),
        errorMessage.build(),

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
    customComponents = arrayOf(
        "trailingIcon" to trailingIcon
    )
)