package com.vini.featurelogin.ui.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun NonDismissibleDialog(
    content: @Composable () -> Unit
) = Dialog(
    onDismissRequest = { },
    DialogProperties(
        dismissOnBackPress = false,
        dismissOnClickOutside = false
    )
) { content() }