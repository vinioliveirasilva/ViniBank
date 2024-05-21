package com.vini.featuresignup.steps.personalinfo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PersonalInfoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PersonalInfoUiState())
    val uiState: StateFlow<PersonalInfoUiState> = _uiState.asStateFlow()

    fun handleEvent(event: PersonalInfoEvent) = when (event) {
        is PersonalInfoEvent.DoOnNameChange -> _uiState.update { it.copy(name = event.name) }
        is PersonalInfoEvent.DoOnDocumentChange -> _uiState.update {
            it.copy(
                document = event.document.take(
                    DOCUMENT_MAX_LENGTH
                )
            )
        }

        is PersonalInfoEvent.DoOnPhoneChange -> _uiState.update {
            it.copy(
                phone = event.phone.take(
                    PHONE_MAX_LENGTH
                )
            )
        }
    }

    private companion object {
        const val DOCUMENT_MAX_LENGTH = 11
        const val PHONE_MAX_LENGTH = 11
    }
}