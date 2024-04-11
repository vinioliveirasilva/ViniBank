package com.vini.featuresignup.steps.createpassword

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.vini.designsystem.xml.view.BaseFragment
import com.vini.designsystem.xml.view.viewbinding.viewBinding
import com.vini.featuresignup.FlowManager
import com.vini.featuresignup.R
import com.vini.featuresignup.databinding.FragmentCreatePasswordBinding

class CreatePasswordFragment : BaseFragment(R.layout.fragment_create_password) {
    private val viewModel: CreatePasswordViewModel by viewModels()
    private val binding: FragmentCreatePasswordBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener {
            (requireActivity() as FlowManager).onNext(R.id.createPasswordFragment)
        }
    }
}