package com.vini.featuresignup.steps.email

import android.util.Patterns
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EmailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EmailState(isContinueEnable = false))
    val uiState: StateFlow<EmailState> = _uiState.asStateFlow()

    fun handleEvent(event: EmailUIEvent) = when (event) {
        is EmailUIEvent.OnEmailUpdate -> doOnEmailChange(event.email)
        else -> {}
    }

    private fun doOnEmailChange(email: String) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.update { it.copy(isContinueEnable = true) }
        } else {
            _uiState.update { it.copy(isContinueEnable = false) }
        }
    }
}