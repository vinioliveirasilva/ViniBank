package com.example.workouttracker.presentation.add

import androidx.lifecycle.ViewModel
import com.example.workouttracker.presentation.run.UserExerciseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class AddWorkoutAction {
    data class AddExercise(val name: String, val exercises: List<Int>) : AddWorkoutAction()
    data object DismissDialog : AddWorkoutAction()
    data object ShowNameDialog : AddWorkoutAction()
}

class AddWorkoutViewModel(
    private val repository: ExerciseRepository,
    private val userExerciseRepository: UserExerciseRepository,
) : ViewModel() {
    private val _exercises = MutableStateFlow(repository.getExercises())
    val exercises: StateFlow<List<ExerciseModel>> = _exercises


    fun handleAction(event: AddWorkoutAction) {
        when (event) {
            is AddWorkoutAction.AddExercise -> userExerciseRepository.addExerciseToUser(
                event.name,
                event.exercises
            )

            AddWorkoutAction.DismissDialog -> TODO()
            AddWorkoutAction.ShowNameDialog -> TODO()
        }
    }
}