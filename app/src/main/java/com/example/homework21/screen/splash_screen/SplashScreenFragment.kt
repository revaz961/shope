package com.example.homework21.screen.splash_screen

import android.animation.Animator
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homework21.R
import com.example.homework21.databinding.SplashScreenFragmentBinding
import com.example.homework21.screen.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment :
    BaseFragment<SplashScreenFragmentBinding>(SplashScreenFragmentBinding::inflate) {

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun start() {

        binding.lotAnimation.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                if(viewModel.checkSession())
                    findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
                else
                    findNavController().navigate(R.id.action_splashScreenFragment_to_signInFragment)
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
    }
}