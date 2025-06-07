package com.example.serverdriveui.ui.component.components.createpassword

data class CreatePasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordMatch: Boolean = false,
    val hasAtLeastEightCharacters: Boolean = false,
    val hasAtLeastOneUppercaseLetter: Boolean = false,
    val hasAtLeastOneNumber: Boolean = false,
    val hasAtLeastOneSpecialCharacter: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isPasswordVisible: Boolean = false,
)
