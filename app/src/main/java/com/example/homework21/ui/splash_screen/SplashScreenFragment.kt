package com.example.homework21.ui.splash_screen

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homework21.R
import com.example.homework21.databinding.SplashScreenFragmentBinding
import com.example.homework21.ui.BaseFragment

class SplashScreenFragment :
    BaseFragment<SplashScreenFragmentBinding>(SplashScreenFragmentBinding::inflate) {

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun start() {

        if(viewModel.checkSession())
            findNavController().navigate(R.id.action_splashScreenFragment_to_feedFragment)
        else
            findNavController().navigate(R.id.action_splashScreenFragment_to_signInFragment)
    }


}