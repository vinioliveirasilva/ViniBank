package com.vini.featuresignup.steps.accounttype

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.vini.designsystem.xml.view.BaseFragment
import com.vini.designsystem.xml.view.viewbinding.viewBinding
import com.vini.featuresignup.FlowManager
import com.vini.featuresignup.R
import com.vini.featuresignup.databinding.FragmentAccountTypeBinding
import com.vini.featuresignup.steps.createpassword.CreatePasswordViewModel

class AccountTypeFragment : BaseFragment(R.layout.fragment_account_type) {
    private val viewModel: CreatePasswordViewModel by viewModels()
    private val binding: FragmentAccountTypeBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener {
            (requireActivity() as FlowManager).onNext(R.id.accountTypeFragment)
        }
    }
}