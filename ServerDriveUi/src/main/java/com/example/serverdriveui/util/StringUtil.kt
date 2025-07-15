package com.example.serverdriveui.util

import android.text.Html
import android.text.Spanned
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

object StringUtil {
    fun String.toAnnotatedStringFromHtml(): AnnotatedString {
        val spanned: Spanned = Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
        return buildAnnotatedString {
            append(spanned.toString())

            spanned.getSpans(0, spanned.length, Any::class.java).forEach { span ->
                val start = spanned.getSpanStart(span)
                val end = spanned.getSpanEnd(span)

                when (span) {
                    is android.text.style.StyleSpan -> {
                        when (span.style) {
                            android.graphics.Typeface.BOLD -> addStyle(
                                SpanStyle(fontWeight = FontWeight.Bold),
                                start,
                                end
                            )
                            android.graphics.Typeface.ITALIC -> addStyle(
                                SpanStyle(fontStyle = FontStyle.Italic),
                                start,
                                end
                            )
                            android.graphics.Typeface.BOLD_ITALIC -> addStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Italic
                                ),
                                start,
                                end
                            )
                        }
                    }
                    is android.text.style.StrikethroughSpan -> {
                        addStyle(
                            SpanStyle(textDecoration = TextDecoration.LineThrough),
                            start,
                            end
                        )
                    }
                    is android.text.style.UnderlineSpan -> {
                        addStyle(
                            SpanStyle(textDecoration = TextDecoration.Underline),
                            start,
                            end
                        )
                    }
                    is android.text.style.ForegroundColorSpan -> {
                        addStyle(
                            SpanStyle(color = Color(span.foregroundColor)),
                            start,
                            end
                        )
                    }
                    // outros spans podem ser adicionados aqui
                }
            }
        }
    }
}