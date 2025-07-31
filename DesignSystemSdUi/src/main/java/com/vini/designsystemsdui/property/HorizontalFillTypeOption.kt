package com.vini.designsystemsdui.property

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier

enum class HorizontalFillTypeOption(val modifier: Modifier) {
    Max(Modifier.Companion.fillMaxWidth()),
    Half(Modifier.Companion.fillMaxWidth(.5f)),
    Quarter(Modifier.Companion.fillMaxWidth(.25f)),
    Wrap(Modifier.Companion.wrapContentWidth()),
    None(Modifier.Companion),
}