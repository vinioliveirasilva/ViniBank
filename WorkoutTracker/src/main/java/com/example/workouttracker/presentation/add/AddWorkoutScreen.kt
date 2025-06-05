package com.example.workouttracker.presentation.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vini.designsystem.compose.scaffold.BaseScaffold2
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddWorkoutScreen(viewModel: AddWorkoutViewModel = koinViewModel(), onClose: () -> Unit) {
    AddWorkoutUI(
        viewModel.exercises.collectAsState().value,
        viewModel::handleAction,
        onClose
    )
}

@Composable
fun AddWorkoutUI(
    exercises: List<ExerciseModel>,
    onEvent: (AddWorkoutAction) -> Unit,
    onClose: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val muscleGroups = exercises.flatMap { it.muscleGroup }.distinct().sorted()
    var selectedGroup by remember { mutableStateOf<List<String>>(listOf()) }
    val selectedExercises = remember { mutableStateMapOf<Int, Boolean>() }
    var showDialog by remember { mutableStateOf(false) }
    var workoutName by remember { mutableStateOf(TextFieldValue("")) }

    val filteredExercises = exercises.filter { exercise ->
        selectedGroup.isEmpty() || exercise.muscleGroup.any { selectedGroup.contains(it) }
    }

    // Check if any checkbox is selected
    val isAnySelected = selectedExercises.values.contains(true)

    val dialogContent: @Composable () -> Unit = {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Name Your Workout") },
            text = {
                TextField(
                    value = workoutName,
                    onValueChange = { workoutName = it },
                    label = { Text("Workout Name") }
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onEvent(
                            AddWorkoutAction.AddExercise(
                                workoutName.text,
                                selectedExercises.toMap().filter { it.value }.keys.toList()
                            )
                        )
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(onClick = {
                    onEvent(AddWorkoutAction.DismissDialog)
                }) {
                    Text("Cancel")
                }
            }
        )
    }

    BaseScaffold2(
        topBarTitle = "Add Workout",
        header = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Filter",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Gray)
                        .padding(1.dp)
                        .background(Color.White)
                        .clickable { expanded = true }
                        .padding(14.dp),
                    style = MaterialTheme.typography.bodyLarge
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("All") },
                        onClick = {
                            selectedGroup = listOf()
                            expanded = false
                        }
                    )
                    muscleGroups.forEach { group ->
                        DropdownMenuItem(
                            text = { Text(group) },
                            onClick = {
                                selectedGroup = if (selectedGroup.contains(group))
                                    selectedGroup.minus(group) else
                                    selectedGroup.plus(group)
                            },
                            trailingIcon = {
                                Checkbox(
                                    checked = selectedGroup.contains(group),
                                    onCheckedChange = {
                                        selectedGroup = if (selectedGroup.contains(group))
                                            selectedGroup.minus(group) else
                                            selectedGroup.plus(group)
                                    }
                                )
                            }
                        )
                    }
                }
            }
        },
        buttons = {
            Button(
                onClick = {
                    onEvent(AddWorkoutAction.ShowNameDialog)
                },
                enabled = isAnySelected,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit Selected Exercises")
            }
        },
        listContent = {
            items(filteredExercises) { exercise ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            selectedExercises[exercise.id] =
                                selectedExercises.getOrDefault(exercise.id, false).not()
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.Black),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Absolute.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(10f)) {
                            Text(
                                text = exercise.name,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White
                            )
                            Text(
                                text = "Equipment: ${exercise.equipment}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                            Text(
                                text = "Muscle Group: ${exercise.muscleGroup.joinToString(", ")}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White
                            )
                        }
                        Checkbox(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically),
                            checked = selectedExercises[exercise.id] ?: false,
                            onCheckedChange = { isChecked ->
                                selectedExercises[exercise.id] = isChecked
                            }
                        )
                    }
                }
            }
        }
    ) {
        if (showDialog) {
            dialogContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddWorkoutUI() {
    val sampleExercises = listOf(
        ExerciseModel(
            id = 1, name = "Squat", muscleGroup = listOf("Legs"), equipment = "Barbell",
            description = "A compound exercise that targets the legs and glutes by lowering the body and standing back up."
        ),
        ExerciseModel(
            id = 2, name = "Bench Press", muscleGroup = listOf("Chest"), equipment = "Barbell",
            description = "An upper body exercise where you press a barbell away from your chest while lying on a bench."
        ),
        ExerciseModel(
            id = 3, name = "Deadlift", muscleGroup = listOf("Back", "Legs"), equipment = "Barbell",
            description = "A full-body movement that involves lifting a barbell from the ground to a standing position."
        ),
        ExerciseModel(
            id = 4, name = "Pull-Up", muscleGroup = listOf("Back", "Arms"), equipment = "None",
            description = "A bodyweight exercise where you pull yourself up on a bar until your chin is above the bar."
        )
    )

    AddWorkoutUI(exercises = sampleExercises, onEvent = {}, onClose = {})
}