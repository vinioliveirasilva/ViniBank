package com.vini.designsystem.compose.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.vini.designsystem.R

@Stable
@Composable
fun Buttons(
    modifier: Modifier = Modifier,
    primaryAction: () -> Unit,
    primaryText: String,
    primaryIsEnable: Boolean = true,
    secondaryAction: (() -> Unit)? = null,
    secondaryText: String = "",
    secondaryIsEnable: Boolean = true,
    linkAction: (() -> Unit)? = null,
    linkText: String = "",
    linkIsEnable: Boolean = true,
) {
    Row(modifier = modifier) {
        Column {
            Button(
                enabled = primaryIsEnable,
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { primaryAction() }
            ) {
                Text(text = primaryText)
            }

            secondaryAction?.let {
                Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.double_grid)))
                ElevatedButton(
                    enabled = secondaryIsEnable,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { secondaryAction() }
                ) {
                    Text(text = secondaryText)
                }
            }

            linkAction?.let {
                Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.double_grid)))
                OutlinedButton(
                    enabled = linkIsEnable,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { linkAction() }
                ) {
                    Text(text = linkText)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewButtons() {
    Buttons(
        primaryAction = { },
        primaryText = "Botão Primario",
        secondaryAction = { },
        secondaryText = "Botão Secondario",
        linkAction = { },
        linkText = "Botão Link"
    )
}