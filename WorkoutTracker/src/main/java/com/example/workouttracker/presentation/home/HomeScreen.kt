package com.example.workouttracker.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.workouttracker.repository.Workout
import com.vini.designsystem.compose.button.Buttons
import com.vini.designsystem.compose.scaffold.BaseScaffold
import com.vini.designsystem.compose.theme.ViniBankTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    onCloseAction: () -> Unit,
    onExecuteWorkout: (workoutName: String) -> Unit,
    onAddWorkout: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    HomeUi(viewModel.state.collectAsStateWithLifecycle().value, onExecuteWorkout, onAddWorkout) {
        viewModel.handleEvent(it)
    }
}

@Composable
fun HomeUi(
    workoutState: List<Workout>,
    onExecuteWorkout: (workoutName: String) -> Unit,
    onAddWorkout: () -> Unit,
    eventHandler: (HomeEvent) -> Unit
) {
    BaseScaffold(
        topBarTitle = "Home",
        buttons = {
            Buttons(
                primaryAction = onAddWorkout,
                primaryText = "Adicionar Treino"
            )
        }
    ) {
        LaunchedEffect(true) {
            eventHandler.invoke(HomeEvent.DoOnCreate)
        }

        workoutState.forEach {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { onExecuteWorkout.invoke(it.name) }
            ) {
                Text(
                    text = it.name,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    ViniBankTheme {
        HomeUi(
            workoutState = listOf(Workout(1, "Treino 1"), Workout(2, "Treino 2")),
            onExecuteWorkout = {},
            onAddWorkout = {},
            eventHandler = {}
        )
    }
}