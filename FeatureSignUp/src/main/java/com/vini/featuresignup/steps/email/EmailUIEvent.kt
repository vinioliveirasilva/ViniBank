package com.vini.featuresignup.steps.email

sealed class EmailUIEvent {
    data object DisableContinueButton : EmailUIEvent()
    data object EnableContinueButton : EmailUIEvent()
    data object PlaySuccessAnimation: EmailUIEvent()
}