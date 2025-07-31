package com.vini.designsystemsdui.property

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

enum class ShapeOptions(val shape: Shape) {
    None(RoundedCornerShape(0.dp)),
    Small(RoundedCornerShape(4.dp)),
    Medium(RoundedCornerShape(8.dp)),
    Large(RoundedCornerShape(16.dp)),
    Circle(CircleShape),
}