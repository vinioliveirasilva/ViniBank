package com.vini.featuresignup.steps.email

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.vini.common.mvvm.sendInScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class EmailViewModel : ViewModel() {

    private val _event = Channel<EmailUIEvent>()
    val event = _event.receiveAsFlow()
    fun doOnCreate() {
        _event.sendInScope(this, EmailUIEvent.DisableContinueButton)
    }

    fun doOnEmailChange(email: String) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _event.sendInScope(
                this,
                EmailUIEvent.EnableContinueButton,
                EmailUIEvent.PlaySuccessAnimation
            )
        } else {
            _event.sendInScope(this, EmailUIEvent.DisableContinueButton)
        }
    }
}