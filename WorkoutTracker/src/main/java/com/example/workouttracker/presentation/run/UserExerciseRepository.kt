package com.example.workouttracker.presentation.run

import com.example.workouttracker.presentation.add.ExerciseModel
import com.example.workouttracker.presentation.add.ExerciseRepository

class UserExerciseRepository(
    private val exerciseRepository: ExerciseRepository
) {
    private val userExercises = mutableMapOf<String, List<ExerciseModel>>()

    fun addExerciseToUser(exerciseName: String, exercises: List<Int>) {
        userExercises[exerciseName] = exerciseRepository.getExercises().filter { it.id in exercises }
    }

    fun getExercisesById(exerciseName: String): List<ExerciseModel> {
        return userExercises[exerciseName].orEmpty()
    }

    fun getExercises(): Map<String, List<ExerciseModel>> {
        return userExercises
    }
}