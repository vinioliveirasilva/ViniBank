package com.vini.designsystemsdui.property


sealed class VerticalArrangementOption(
    val name: String
) {
    object Top : VerticalArrangementOption("Top")
    object Bottom : VerticalArrangementOption("Bottom")
    object Center : VerticalArrangementOption("Center")
    object SpaceAround : VerticalArrangementOption("SpaceAround")
    object SpaceBetween : VerticalArrangementOption("SpaceBetween")
    object SpaceEvenly : VerticalArrangementOption("SpaceEvenly")
    class SpacedBy(val space: Int) : VerticalArrangementOption("SpacedBy$space")
}