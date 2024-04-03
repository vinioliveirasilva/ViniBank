package com.vini.featurelogin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.vini.designsystem.loader.LoaderView
import com.vini.designsystem.loader.LoaderViewComponent
import com.vini.designsystem.view.BaseActivity
import com.vini.designsystem.view.viewbinding.viewBinding
import com.vini.featurelogin.databinding.LoginActivityBinding
import com.vini.common.mvvm.observe
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity :
    BaseActivity(R.layout.login_activity),
    LoaderView by LoaderViewComponent()
{

    private val viewModel by viewModel<LoginViewModel>()
    private val binding by viewBinding<LoginActivityBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.event, ::handleEvent)
        viewModel.doOnCreate()
    }

    private fun handleEvent(event: LoginUIEvent) : Any = when(event) {
        is LoginUIEvent.SetupEmail -> binding.emailInput.setText(event.email)
        is LoginUIEvent.ShowLoader -> showLoader()
        is LoginUIEvent.HideLoader -> hideLoader()
        is LoginUIEvent.ShowAlert -> Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
        is LoginUIEvent.BusinessSuccess -> {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        with(binding) {
            doLoginButton.setOnClickListener {
                viewModel.doOnLogin(
                    email = emailInput.text.toString(),
                    pass = passInput.text.toString(),
                )
            }
        }
    }

    private fun showLoader() = show(supportFragmentManager)
    private fun hideLoader() = hide()

    companion object {
        fun newIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}