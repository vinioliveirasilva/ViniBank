package com.vini.featuresignup.steps.personalinfo

sealed class PersonalInfoEvent {
    data class DoOnNameChange(val name: String) : PersonalInfoEvent()
    data class DoOnDocumentChange(val document: String) : PersonalInfoEvent()
    data class DoOnPhoneChange(val phone: String) : PersonalInfoEvent()
}
