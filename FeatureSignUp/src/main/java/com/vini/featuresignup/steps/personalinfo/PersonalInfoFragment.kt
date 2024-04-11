package com.vini.featuresignup.steps.personalinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.vini.designsystem.xml.view.BaseFragment
import com.vini.designsystem.xml.view.viewbinding.viewBinding
import com.vini.featuresignup.FlowManager
import com.vini.featuresignup.R
import com.vini.featuresignup.databinding.FragmentPersonalInfoBinding

class PersonalInfoFragment : BaseFragment(R.layout.fragment_personal_info) {
    private val viewModel: PersonalInfoViewModel by viewModels()
    private val binding: FragmentPersonalInfoBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener {
            (requireActivity() as FlowManager).onNext(R.id.personalInfoFragment)
        }
    }
}