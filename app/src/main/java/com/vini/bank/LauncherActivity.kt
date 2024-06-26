package com.vini.bank

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.vini.common.mvvm.observe
import com.vini.designsystem.xml.view.BaseActivity
import com.vini.featurelogin.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LauncherActivity : BaseActivity(com.vini.designsystem.R.layout.main_content) {

    private val viewModel: LauncherViewModel by viewModel()

    private val loginLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            goToHome()
        } else {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.event, ::handleEvent)
        savedInstanceState ?: viewModel.doOnCreate()
    }

    private fun handleEvent(event: LauncherUIEvent) = when(event) {
        is LauncherUIEvent.OpenHome -> goToHome()
        is LauncherUIEvent.OpenLogin -> goToLogin()
    }

    private fun goToHome() = Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show()

    private fun goToLogin() = loginLauncher.launch(LoginActivity.newIntent(this))
}