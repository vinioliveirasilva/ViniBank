package com.example.workouttracker.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workouttracker.repository.Workout
import com.example.workouttracker.repository.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class HomeViewModel(
    private val workoutRepository: WorkoutRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Workout>())
    val state: StateFlow<List<Workout>> = _state

    fun handleEvent(event: HomeEvent) = when (event) {
        HomeEvent.DoOnCreate -> handleOnCreateAction()
    }

    private fun handleOnCreateAction() =
        workoutRepository
            .getWorkouts()
            .map { result -> _state.update { result } }
            .launchIn(viewModelScope)
}

sealed class HomeEvent {
    data object DoOnCreate : HomeEvent()

}