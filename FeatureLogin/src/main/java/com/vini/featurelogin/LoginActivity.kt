package com.vini.featurelogin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.vini.designsystem.loader.LoaderView
import com.vini.designsystem.loader.LoaderViewComponent
import com.vini.designsystem.view.BaseActivity
import com.vini.designsystem.view.viewbinding.viewBinding
import com.vini.featurelogin.databinding.LoginActivityBinding
import com.vini.common.mvvm.observe
import com.vini.featuresignup.SignUpActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity :
    BaseActivity(R.layout.login_activity),
    LoaderView by LoaderViewComponent() {

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        viewModel.doOnSignUpResult(it.resultCode)
    }

    private val viewModel by viewModel<LoginViewModel>()
    private val binding by viewBinding<LoginActivityBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.uievent, ::handleEvent)
        //viewModel.doOnCreate()
    }

    private fun handleEvent(event: LoginUIEvent): Any = when (event) {
        is LoginUIEvent.OpenSignUp -> signUpLauncher.launch(SignUpActivity.newIntent(this))
        //is LoginUIEvent.SetupEmail -> binding.emailInput.setText(event.email)
       // is LoginUIEvent.ShowLoader -> showLoader()
        //is LoginUIEvent.HideLoader -> hideLoader()
        is LoginUIEvent.ShowAlert -> Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
        is LoginUIEvent.BusinessSuccess -> {
            setResult(RESULT_OK)
            finish()
        }

        LoginUIEvent.Empty -> TODO()
    }

    override fun onStart() {
        super.onStart()
        with(binding) {
            doLoginButton.setOnClickListener {
                startActivity(LoginActivityC.newIntent(this@LoginActivity))
                return@setOnClickListener
                viewModel.doOnLogin(
                    email = emailInput.text.toString(),
                    pass = passInput.text.toString(),
                )
            }
            doSignUpButton.setOnClickListener { viewModel.openSignUp() }
        }
    }

    private fun showLoader() = show(supportFragmentManager)
    private fun hideLoader() = hide()

    companion object {
        fun newIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}