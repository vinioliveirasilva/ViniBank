package com.example.workouttracker.presentation.run

import androidx.lifecycle.ViewModel
import com.example.workouttracker.presentation.add.ExerciseModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserExerciseViewModel(
    private val workoutName: String,
    private val userExerciseRepository: UserExerciseRepository
) : ViewModel() {

    private val _userExercises = MutableStateFlow(userExerciseRepository.getExercisesById(workoutName))
    val userExercises: StateFlow<List<ExerciseModel>> get() = _userExercises
}