package com.vini.designsystem.compose.scaffold

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.vini.designsystem.R
import com.vini.designsystem.compose.button.Buttons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScaffold(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    topBarTitle: String,
    buttons: @Composable () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = topBarTitle) }
            )
        }
    ) { internalPadding ->
        Column(
            modifier = Modifier
                .padding(internalPadding)
                .padding(dimensionResource(id = R.dimen.quadruple_grid))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5F)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                ) {
                    this.content()
                }
            }

            buttons()
        }
    }
}

@Preview
@Composable
private fun BaseScaffoldPreview() {
    BaseScaffold(
        topBarTitle = "Teste",
        buttons = { Buttons(primaryAction = { /*TODO*/ }, primaryText = "abc") },
        content = {
            Text("a")
            Text("b")
            Text("c")
            Text("d")
            Text("e")
            Text("f")
            Text("g")
        }
    )
}