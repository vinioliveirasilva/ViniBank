package com.vini.designsystemsdui.property

import androidx.compose.ui.text.font.FontWeight

enum class FontWeightOption(val fontWeight: FontWeight) {
    Thin(FontWeight.Companion.Thin),
    ExtraLight(FontWeight.Companion.ExtraLight),
    Light(FontWeight.Companion.Light),
    Normal(FontWeight.Companion.Normal),
    Medium(FontWeight.Companion.Medium),
    SemiBold(FontWeight.Companion.SemiBold),
    Bold(FontWeight.Companion.Bold),
    ExtraBold(FontWeight.Companion.ExtraBold),
    Black(FontWeight.Companion.Black),
    Default(FontWeight.Companion.Normal),
}