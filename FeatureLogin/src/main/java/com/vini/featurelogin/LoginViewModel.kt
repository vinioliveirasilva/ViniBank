package com.vini.featurelogin

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vini.common.mvvm.sendInScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val state: SavedStateHandle,
    private val loginRepository: LoginRepository,
) : ViewModel() {

    private val _event = Channel<LoginUIEvent>()
    val event = _event.receiveAsFlow()

    fun doOnCreate() {
        _event.sendInScope(this, LoginUIEvent.SetupEmail(state.get<String>(Keys.email).orEmpty()))
    }

    fun doOnLogin(email: String, pass: String) {
        state[Keys.email] = email
        viewModelScope.launch {
            loginRepository.doLogin(email, pass)
                .catch {
                    _event.sendInScope(
                        this@LoginViewModel,
                        LoginUIEvent.HideLoader,
                        LoginUIEvent.ShowAlert(it.message)
                    )
                }
                .onStart {
                    _event.sendInScope(this@LoginViewModel, LoginUIEvent.ShowLoader)
                }.onCompletion {
                    _event.sendInScope(this@LoginViewModel, LoginUIEvent.HideLoader)
                }.collectLatest {
                    _event.sendInScope(this@LoginViewModel, LoginUIEvent.BusinessSuccess)
                }
        }
    }

    private companion object {
        object Keys {
            const val email = "EMAIL"
        }
    }
}