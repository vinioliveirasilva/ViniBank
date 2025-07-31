package com.vini.designsystemsdui.property

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.vini.designsystem.compose.textfield.MaskVisualTransformation

enum class VisualTransformationOption(
    val visualTransformation: VisualTransformation,
) {
    None(VisualTransformation.None),
    Password(PasswordVisualTransformation()),
    Phone(
        MaskVisualTransformation(
            mask = "## #####-####",
            toIgnore = '#'
        )
    ),
    CpfDocument(
        MaskVisualTransformation(
            mask = "###.###.###-##",
            toIgnore = '#'
        )
    ),
}
