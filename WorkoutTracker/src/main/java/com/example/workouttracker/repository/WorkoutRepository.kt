package com.example.workouttracker.repository

import com.example.workouttracker.presentation.run.UserExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface WorkoutRepository {
    fun getWorkouts(): Flow<List<Workout>>
}

class WorkoutRepositoryImpl(
    private val user: UserExerciseRepository,
) : WorkoutRepository {
    override fun getWorkouts() = flow {
        emit(
            user.getExercises().map {
                Workout(0, it.key)
            }
        )
    }
}

data class Workout(val id: Int, val name: String)
