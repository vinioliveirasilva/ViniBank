package com.vini.designsystemsdui.property

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier

enum class VerticalFillTypeOption(val modifier: Modifier) {
    Max(Modifier.fillMaxHeight()),
    Half(Modifier.fillMaxHeight(.5f)),
    Quarter(Modifier.fillMaxHeight(.25f)),
    Wrap(Modifier.wrapContentHeight()),
    None(Modifier),
}