package com.example.workouttracker.presentation.run

import com.example.workouttracker.presentation.add.ExerciseModel

data class UserExerciseModel(
    val exerciseId: Int,
    val exercises: List<ExerciseModel>
)

