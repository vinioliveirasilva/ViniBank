package com.vini.featurelogin

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.common.mvvm.sendInScope
import com.vini.designsystem.compose.loader.LoaderComponent
import com.vini.designsystem.compose.loader.LoaderComponentViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class LoginViewModel(
    private val loginRepository: LoginRepository,
) : ViewModel(),
    LoaderComponent by LoaderComponentViewModel() {

    private val _vmEvent = Channel<LoginVMEvent>()
    val vmEvent = _vmEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    private fun doLogin(email: String, pass: String) {
        viewModelScope.launch {
            loginRepository.doLogin(email, pass)
                .catch {
                    _uiState.update { currentState -> currentState.copy(snackBarError = it.message) }
                }.onStart {
                    showLoader()
                }.onCompletion {
                    hideLoader()
                }.collectLatest {
                    _vmEvent.sendInScope(this@LoginViewModel, LoginVMEvent.BusinessSuccess)
                }
        }
    }

    private fun onSignUpResult(resultCode: Int) {
        if (resultCode == Activity.RESULT_OK) {
            _vmEvent.sendInScope(this@LoginViewModel, LoginVMEvent.BusinessSuccess)
        }
    }

    fun handleEvent(loginUIEvent: LoginUIEvent) = when(loginUIEvent) {
        is LoginUIEvent.DoOnLogin -> doLogin(_uiState.value.email, _uiState.value.pass)
        is LoginUIEvent.DoOnSignUp -> onSignUpResult(loginUIEvent.resultCode)
        is LoginUIEvent.DoOnDismissSnackBar -> _uiState.update { it.copy(snackBarError = null) }
        is LoginUIEvent.DoOnEmailChange -> _uiState.update { it.copy(email = loginUIEvent.email) }
        is LoginUIEvent.DoOnPassChange -> _uiState.update { it.copy(pass = loginUIEvent.pass) }
    }
}