package com.vini.featuresignup.steps.createpassword

data class CreatePasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordMatch: Boolean = false,
    val hasAtLeastEightCharacters: Boolean = false,
    val hasAtLeastOneUppercaseLetter: Boolean = false,
    val hasAtLeastOneNumber: Boolean = false,
    val hasAtLeastOneSpecialCharacter: Boolean = false,
    val isContinueEnabled: Boolean = false,
    val isPasswordVisible: Boolean = false,
)
