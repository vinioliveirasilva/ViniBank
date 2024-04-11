package com.vini.featuresignup.steps.email

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vini.common.mvvm.observe
import com.vini.designsystem.xml.view.BaseFragment
import com.vini.designsystem.xml.view.viewbinding.viewBinding
import com.vini.featuresignup.FlowManager
import com.vini.featuresignup.R
import com.vini.featuresignup.databinding.FragmentEmailBinding

class EmailFragment : BaseFragment(R.layout.fragment_email) {
    private val viewModel: EmailViewModel by viewModels()
    private val binding: FragmentEmailBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.event, ::handleEvent)
    }

    private fun handleEvent(event: EmailUIEvent) = when(event) {
        EmailUIEvent.DisableContinueButton -> binding.emailContinue.isEnabled = false
        EmailUIEvent.EnableContinueButton -> binding.emailContinue.isEnabled = true
        EmailUIEvent.PlaySuccessAnimation -> {
            if(!binding.lottieAnimationView2.isAnimating) {
                binding.lottieAnimationView2.playAnimation()
            } else {
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.doOnCreate()
        binding.textField.editText?.doOnTextChanged { text, _, _, _ ->
            viewModel.doOnEmailChange(text.toString())
        }
    }
}