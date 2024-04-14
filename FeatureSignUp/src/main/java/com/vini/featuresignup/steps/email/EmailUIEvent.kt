package com.vini.featuresignup.steps.email

sealed class EmailUIEvent {
    class OnEmailUpdate(val email: String): EmailUIEvent()
}