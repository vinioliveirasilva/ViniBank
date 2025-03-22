package com.vini.designsystem.compose.textfield

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class MaskVisualTransformation(
    private val mask: String,
    private val toIgnore: Char
) : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return documentMaskFilter(text)
    }

    private val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            var appendedChars = 0
            return mask.filterIndexed { index, char ->
                if (index >= offset.plus(appendedChars)) {
                    false
                } else {
                    (char != toIgnore).also {
                        if (it) appendedChars++
                    }
                }
            }.length.plus(offset)
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.filterIndexed { index, char ->
                if (index <= offset) char != toIgnore
                else false
            }.length.minus(offset).absoluteValue
        }
    }

    private fun documentMaskFilter(text: AnnotatedString): TransformedText {
        var out = ""
        var internalIndex = 0

        text.text.forEach {
            if (mask[internalIndex] != toIgnore) {
                out += mask[internalIndex]
                internalIndex++
            }
            out += it
            internalIndex++
        }

        return TransformedText(AnnotatedString(out), numberOffsetTranslator)
    }
}