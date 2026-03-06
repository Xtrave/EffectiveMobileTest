package com.example.effectivemobiletest.main.presentation

sealed interface MainState {
    data object Loading: MainState
    data class Error(val message: String): MainState
    data class Content(val course: List<CourseItem>): MainState
}