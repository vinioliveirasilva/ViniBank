package com.vini.featuresignup.steps.accounttype

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.featuresignup.R
import org.koin.androidx.compose.koinViewModel


@Composable
fun AccountTypeScreen(
    onBusinessSuccess: () -> Unit,
    onBusinessFailure: () -> Unit,
    viewModel: AccountTypeViewModel = koinViewModel()
) {
    AccountTypeUI(
        onBusinessSuccess,
        onBusinessFailure,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AccountTypeUI(
    onBusinessSuccess: () -> Unit,
    onBusinessFailure: () -> Unit,
) {
    ViniBankTheme {
        val snackBarHostState = remember { SnackbarHostState() }

        Scaffold(
            modifier = Modifier
                .padding(dimensionResource(id = com.vini.designsystem.R.dimen.quadruple_grid))
                .background(Color.Cyan, RectangleShape),
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.account_type_title)
                        )
                    }
                )
            }
        ) { internalPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(internalPadding)
            ) {
                Text(
                    text = stringResource(id = R.string.account_type_description),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onBusinessSuccess() }
                        .size(32.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAccountTypeUI() {
    AccountTypeUI(
        onBusinessSuccess = { },
        onBusinessFailure = { }
    )
}