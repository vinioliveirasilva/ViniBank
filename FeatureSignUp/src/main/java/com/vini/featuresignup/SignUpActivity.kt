package com.vini.featuresignup

import android.content.Context
import android.content.Intent
import androidx.annotation.IdRes
import androidx.navigation.findNavController
import com.vini.designsystem.xml.view.BaseActivity
import com.vini.designsystem.xml.view.viewbinding.viewBinding
import com.vini.featuresignup.databinding.ActivitySignUpBinding

interface FlowManager {
    fun onNext(@IdRes fromFragment: Int)
}

class SignUpActivity : BaseActivity(R.layout.activity_sign_up), FlowManager {

    private val binding: ActivitySignUpBinding by viewBinding()
    private val navController by lazy {
        findNavController(binding.fragmentContainerView.id)
    }
    override fun onNext(fromFragment: Int) {
        val actionId = when(fromFragment) {
            R.id.emailFragment -> R.id.action_emailFragment_to_personalInfoFragment
            R.id.personalInfoFragment -> R.id.action_personalInfoFragment_to_accountTypeFragment
            R.id.accountTypeFragment -> R.id.action_accountTypeFragment_to_createPasswordFragment
            else -> FINISH_ACTION
        }

        if(actionId != FINISH_ACTION) {
            navController.navigate(actionId)
        } else {
            finish()
        }
    }

    companion object {
        private const val FINISH_ACTION = 0
        fun newIntent(context: Context) = Intent(context, SignUpActivity::class.java)
    }
}