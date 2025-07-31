package com.vini.designsystemsdui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vini.designsystem.compose.textfield.MaskVisualTransformation

/**
 * Central place for all option enums used by component properties.
 */
object PropertyOptions {
    enum class AlignmentOptions(val id: String, val alignment: Alignment) {
        TopStart("TopStart", Alignment.TopStart),
        TopCenter("TopCenter", Alignment.TopCenter),
        TopEnd("TopEnd", Alignment.TopEnd),
        CenterStart("CenterStart", Alignment.CenterStart),
        Center("Center", Alignment.Center),
        CenterEnd("CenterEnd", Alignment.CenterEnd),
        BottomStart("BottomStart", Alignment.BottomStart),
        BottomCenter("BottomCenter", Alignment.BottomCenter),
        BottomEnd("BottomEnd", Alignment.BottomEnd),
    }

    enum class HorizontalAlignmentOptions(val id: String, val alignment: Alignment.Horizontal) {
        Center("Center", Alignment.CenterHorizontally),
        Start("Start", Alignment.Start),
        End("End", Alignment.End),
    }

    enum class HorizontalArrangementOptions(val id: String, val arrangement: Arrangement.Horizontal) {
        Start("Start", Arrangement.Start),
        End("End", Arrangement.End),
        Center("Center", Arrangement.Center),
        SpaceBetween("SpaceBetween", Arrangement.SpaceBetween),
        SpaceAround("SpaceAround", Arrangement.SpaceAround),
    }

    enum class HorizontalFillTypeOption(val id: String, val modifier: Modifier) {
        Max("Max", Modifier.fillMaxWidth()),
        Half("Half", Modifier.fillMaxWidth(.5f)),
        Quarter("Quarter", Modifier.fillMaxWidth(.25f)),
        Wrap("Wrap", Modifier.wrapContentWidth()),
        None("", Modifier),
    }

    enum class KeyboardOptionsOption(val id: String, val keyboardOptions: KeyboardOptions) {
        Default("Default", KeyboardOptions.Default),
        Number("Number", KeyboardOptions(keyboardType = KeyboardType.Number)),
        Phone("Phone", KeyboardOptions(keyboardType = KeyboardType.Phone)),
        Password("Password", KeyboardOptions(keyboardType = KeyboardType.Password)),
    }

    enum class ShapeOptions(val id: String, val shape: Shape) {
        None("None", RoundedCornerShape(0.dp)),
        Small("Small", RoundedCornerShape(4.dp)),
        Medium("Medium", RoundedCornerShape(8.dp)),
        Large("Large", RoundedCornerShape(16.dp)),
        Circle("Circle", CircleShape),
    }

    enum class TextAlignOption(val id: String, val textAlign: TextAlign) {
        Start("Start", TextAlign.Start),
        Center("Center", TextAlign.Center),
        End("End", TextAlign.End),
    }

    enum class VerticalAlignmentOption(val id: String, val verticalAlignment: Alignment.Vertical) {
        Top("Top", Alignment.Top),
        Center("Center", Alignment.CenterVertically),
        Bottom("Bottom", Alignment.Bottom),
    }

    enum class VerticalFillTypeOption(val id: String, val modifier: Modifier) {
        Max("Max", Modifier.fillMaxHeight()),
        Half("Half", Modifier.fillMaxHeight(.5f)),
        Quarter("Quarter", Modifier.fillMaxHeight(.25f)),
        Wrap("Wrap", Modifier.wrapContentHeight()),
        None("", Modifier),
    }

    enum class VisualTransformationOption(val id: String, val visualTransformation: VisualTransformation) {
        None("None", VisualTransformation.None),
        Password("Password", PasswordVisualTransformation()),
        Phone(
            "Phone",
            MaskVisualTransformation(
                mask = "## #####-####",
                toIgnore = '#'
            )
        ),
        Document(
            "Documento.CPF",
            MaskVisualTransformation(
                mask = "###.###.###-##",
                toIgnore = '#'
            )
        ),
    }
}
