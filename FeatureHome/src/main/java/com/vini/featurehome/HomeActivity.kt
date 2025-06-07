package com.vini.featurehome

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.router.FeatureRouter
import com.example.router.routes.LauncherRoute
import com.vini.common.mvvm.observe
import com.vini.designsystem.compose.loader.Loader
import com.vini.designsystem.compose.loader.LoaderState
import com.vini.designsystem.compose.loader.loaderStateMock
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.designsystem.compose.view.BaseComposeActivity
import kotlinx.coroutines.flow.StateFlow
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.scope.KoinActivityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeActivity : BaseComposeActivity() {

    private val viewModel: HomeViewModel by viewModel()
    private val featureRouter: FeatureRouter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.uiEvent) {
            when (it) {
                HomeUiEvent.DoOnLogout -> featureRouter.navigateAndFinish(LauncherRoute())
            }
        }
        setContent {
            KoinActivityScope {
                HomeUi(
                    viewModel::handleEvent,
                    viewModel.selectedContent.collectAsStateWithLifecycle().value,
                    viewModel.loaderState,
                )
            }
        }
    }
}

@Composable
fun HomeUi(
    eventHandler: (HomeEvent) -> Unit,
    homeContent: HomeContentModel,
    loaderState: StateFlow<LoaderState>,
) {

    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(true) {
        eventHandler.invoke(HomeEvent.GoToContent(selectedItem))
    }
    Loader(loaderState)
    Scaffold(
        bottomBar = {
            NavigationBar(modifier = Modifier.height(64.dp)) {
                NavigationBar {
                    homeContent.titles.forEachIndexed { index, item ->
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    homeContent.icons[index],
                                    contentDescription = item
                                )
                            },
                            label = { Text(item) },
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                eventHandler.invoke(HomeEvent.GoToContent(index))
                            }
                        )
                    }
                }
            }
        },
        topBar = {
            HomeTopBar(
                onLogoutClick = { eventHandler.invoke(HomeEvent.Logout) },
                onUserClick = { eventHandler.invoke(HomeEvent.GoToUser) }
            )
        }
    ) { padding -> homeContent.content(Modifier.padding(padding)) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onLogoutClick: () -> Unit,
    onUserClick: () -> Unit
) {
    TopAppBar(
        title = { Text("Home") },
        actions = {
            IconButton(onClick = onUserClick) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "User"
                )
            }
            IconButton(onClick = onLogoutClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout,
                    contentDescription = "Logout"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun HomeUIPreview() {
    ViniBankTheme {
        HomeUi(
            eventHandler = {},
            homeContent = HomeContentModel(
                content = {},
                icons = listOf(Icons.Default.Home),
                titles = listOf("Home")
            ),
            loaderState = loaderStateMock(false)
        )
    }
}