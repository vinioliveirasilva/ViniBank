package com.example.workouttracker

import com.example.workouttracker.presentation.home.HomeViewModel
import com.example.workouttracker.presentation.add.ExerciseRepository
import com.example.workouttracker.presentation.add.AddWorkoutViewModel
import com.example.workouttracker.presentation.run.UserExerciseRepository
import com.example.workouttracker.presentation.run.UserExerciseViewModel
import com.example.workouttracker.repository.WorkoutRepository
import com.example.workouttracker.repository.WorkoutRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val FeatureModule = module {
    factoryOf(::WorkoutRepositoryImpl) bind WorkoutRepository::class
    viewModelOf(::HomeViewModel)
    single { ExerciseRepository() }
    single { UserExerciseRepository(get()) }
    viewModel { AddWorkoutViewModel(get(), get()) }
    viewModel { (params: String) -> UserExerciseViewModel(params, get()) }
}