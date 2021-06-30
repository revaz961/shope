package com.example.homework21.ui.signin

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.homework21.R
import com.example.homework21.databinding.SignInFragmentBinding
import com.example.homework21.extension.hintColor
import com.example.homework21.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
//    override fun start() {
//        init()
//    }
private val viewModel:SignInViewModel by viewModels()
    private lateinit var binding:SignInFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
            binding = SignInFragmentBinding.inflate(inflater,container,false)
            init()
        return binding.root
    }

    private fun init() {
        initView()
        observes()
    }

    private fun initView() {
        binding.titEmail.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilEmail.hintColor(R.color.text_hint)
            else
                binding.tilEmail.hintColor(R.color.text_color)
        }

        binding.titPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilPassword.hintColor(R.color.text_hint)
            else
                binding.tilPassword.hintColor(R.color.text_color)
        }

        binding.btnSignIn.root.setOnClickListener {
            viewModel.login(binding.titEmail.text.toString(), binding.titPassword.text.toString())
        }
    }

    private fun observes() {
        viewModel.loginLiveData.observe(this, {
            d("userIs",it.toString())
        })
        viewModel.registerLiveData.observe(this, {

        })
    }

}