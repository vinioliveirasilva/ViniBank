package com.vini.featuresignup.steps.email

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.vini.designsystem.view.BaseFragment
import com.vini.designsystem.view.viewbinding.viewBinding
import com.vini.featuresignup.FlowManager
import com.vini.featuresignup.R
import com.vini.featuresignup.databinding.FragmentEmailBinding

class EmailFragment : BaseFragment(R.layout.fragment_email) {
    private val viewModel: EmailViewModel by viewModels()
    private val binding: FragmentEmailBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.setOnClickListener {
            (requireActivity() as FlowManager).onNext(R.id.emailFragment)
        }
    }
}