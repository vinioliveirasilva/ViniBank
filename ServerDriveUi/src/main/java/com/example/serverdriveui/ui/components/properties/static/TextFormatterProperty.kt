package com.example.serverdriveui.ui.components.properties.static

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import com.vini.designsystem.compose.textfield.MaskVisualTransformation

class TextFormatterProperty(staticProperties: Map<String, String>) : TextFormatterComponentProperty {
    override val visualTransformation: VisualTransformation = staticProperties["textFormatter"].toVisualTransformation()
    override val keyboardOptions: KeyboardOptions = staticProperties["textFormatter"].toKeyboardType()

    private fun String?.toVisualTransformation() = when (this) {
        "Documento.CPF" -> MaskVisualTransformation(
            mask = "###.###.###-##",
            toIgnore = '#'
        )
        "Telefone" -> MaskVisualTransformation(
            mask = "## #####-####",
            toIgnore = '#'
        )
        else -> VisualTransformation.None
    }

    private fun String?.toKeyboardType() = when (this) {
        "Documento.CPF" -> KeyboardOptions(keyboardType = KeyboardType.Number)
        "Telefone" -> KeyboardOptions(keyboardType = KeyboardType.Phone)
        else -> KeyboardOptions.Default
    }
}