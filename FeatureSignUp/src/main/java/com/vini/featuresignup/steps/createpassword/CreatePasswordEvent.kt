package com.vini.featuresignup.steps.createpassword

sealed class CreatePasswordEvent {
    class DoOnPasswordChange(val password: String) : CreatePasswordEvent()
    class DoOnConfirmPasswordChange(val confirmPassword: String) : CreatePasswordEvent()
    class DoOnShowPasswordChange(val isVisible: Boolean) : CreatePasswordEvent()
}
