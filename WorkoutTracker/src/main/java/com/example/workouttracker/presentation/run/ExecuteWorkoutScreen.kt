package com.example.workouttracker.presentation.run

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.workouttracker.presentation.add.ExerciseModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun ExecuteWorkoutScreen(
    workoutName: String,
    viewModel: UserExerciseViewModel = koinViewModel { parametersOf(workoutName) },
) {

    ExerciseUI(
        exercisesState = viewModel.userExercises.collectAsState(),
    )
}

@Composable
fun ExerciseUI(exercisesState: State<List<ExerciseModel>>) {
    val exercises = exercisesState.value

    if (exercises.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No exercises available.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(24.dp)
            )
        }
    } else {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(exercises) { exercise ->
                ExerciseCard(exercise)
            }
        }
    }
}

@Composable
private fun ExerciseCard(exercise: ExerciseModel) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = exercise.name, style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "Muscle Group: ${exercise.muscleGroup}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Equipment: ${exercise.equipment}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = exercise.description, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExerciseCardPreview() {
    val sampleExercise = ExerciseModel(
        id = 1,
        name = "Push Ups",
        muscleGroup = listOf("Chest"),
        equipment = "None",
        description = "A basic bodyweight exercise."
    )
    //ExerciseUI(MutableState(listOf(sampleExercise)))
}
