package com.vini.featurelogin

import android.app.Activity
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.vini.featurelogin.ui.loader.LoaderComponent
import com.vini.featurelogin.ui.loader.LoaderComponentViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
) : ViewModel(), LoaderComponent by LoaderComponentViewModel() {

    val event = MutableStateFlow<LoginUIEvent>(value = LoginUIEvent.Empty)
    val event1 = event.asStateFlow()

    private val _uiEvent = Channel<LoginUIEvent>()
    val uievent = _uiEvent.receiveAsFlow()

    init {
        setupLoader(viewModelScope)
    }

    fun doOnLogin(email: String, pass: String) {
        viewModelScope.launch {
            loginRepository.doLogin(email, pass)
                .catch {
                    hideLoader()
                    event.value = LoginUIEvent.ShowAlert(it.message)
                }
                .onStart {
                    showLoader()
                }.onCompletion {
                    hideLoader()
                }.collectLatest {
                    event.value = LoginUIEvent.BusinessSuccess

                }
        }
    }

    fun openSignUp() {
        event.value = LoginUIEvent.OpenSignUp
    }

    fun doOnSignUpResult(resultCode: Int) {
        if (resultCode == Activity.RESULT_OK) {
            event.value = LoginUIEvent.BusinessSuccess
        }
    }
}