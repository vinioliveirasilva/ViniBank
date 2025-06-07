package com.example.serverdriveui.ui.component.components.createpassword

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreatePasswordViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CreatePasswordState())
    val uiState: StateFlow<CreatePasswordState> = _uiState.asStateFlow()
    fun onEvent(event: CreatePasswordEvent) = when (event) {
        is CreatePasswordEvent.DoOnPasswordChange -> updatePassword(event)
        is CreatePasswordEvent.DoOnConfirmPasswordChange -> updateConfirmPassword(event)
        is CreatePasswordEvent.DoOnShowPasswordChange -> _uiState.update { it.copy(isPasswordVisible = event.isVisible) }
    }

    private fun updatePassword(event: CreatePasswordEvent.DoOnPasswordChange) = with(event) {
        _uiState.update { currentState ->
            currentState.copy(
                password = password,
                isPasswordMatch = password == currentState.confirmPassword,
                hasAtLeastEightCharacters = password.length >= MIN_PASSWORD_LENGTH,
                hasAtLeastOneNumber = password.any { it.isDigit() },
                hasAtLeastOneSpecialCharacter = password.any { it.isLetterOrDigit().not() },
                hasAtLeastOneUppercaseLetter = password.any { it.isUpperCase() },
            )
        }
        updateContinueButtonState()
    }

    private fun updateConfirmPassword(event: CreatePasswordEvent.DoOnConfirmPasswordChange) =
        with(event) {
            _uiState.update { currentState ->
                currentState.copy(
                    confirmPassword = confirmPassword,
                    isPasswordMatch = currentState.password == confirmPassword,
                )
            }
            updateContinueButtonState()
        }

    private fun updateContinueButtonState() {
        //deve ser feito dessa forma?
        _uiState.update { currentState ->
            currentState.copy(
                isPasswordValid =
                currentState.isPasswordMatch &&
                        currentState.hasAtLeastEightCharacters &&
                        currentState.hasAtLeastOneUppercaseLetter &&
                        currentState.hasAtLeastOneNumber &&
                        currentState.hasAtLeastOneSpecialCharacter
            )
        }
    }

    private companion object {
        const val MIN_PASSWORD_LENGTH = 8
    }
}