package com.vini.featuresignup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.storage.model.UserData
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: SignUpRepository,
) : ViewModel() {

    private var userData = UserData(name = "", email = "", password = "")

    fun storeEmail(email: String) {
        userData.email = email
    }

    fun storeUserName(userName: String) {
        userData.name = userName
    }

    fun storePassword(password: String) {
        userData.password = password

        viewModelScope.launch {
            repository.doSignUp(userData).collectLatest {

            }
        }
    }
}