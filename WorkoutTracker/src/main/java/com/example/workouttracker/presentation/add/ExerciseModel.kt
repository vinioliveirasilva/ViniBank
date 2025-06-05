package com.example.workouttracker.presentation.add

data class ExerciseModel(
    val id: Int,  // Unique identifier
    val name: String,
    val muscleGroup: List<String>,
    val equipment: String,
    val description: String
)