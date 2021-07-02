package com.example.homework21.ui.signin

import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.example.homework21.R
import com.example.homework21.databinding.SignInFragmentBinding
import com.example.homework21.extension.setColorState
import com.example.homework21.extension.setIconEnd
import com.example.homework21.extension.showIf
import com.example.homework21.network.ResultHandler
import com.example.homework21.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<SignInFragmentBinding>(
    SignInFragmentBinding::inflate
) {
    override fun start() {
        init()
    }

    private val viewModel: SignInViewModel by viewModels()
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    private fun init() {
        initView()
        observes()
    }

    private fun initView() {
        binding.tilEmail.isEndIconVisible = false
        binding.tilPassword.isEndIconVisible = false

        binding.btnSignIn.root.text = getString(R.string.sign_in)

        setListeners()
    }

    private fun setListeners() {
        binding.titEmail.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilEmail.setColorState(R.color.text_hint)
            else
                binding.tilEmail.setColorState(R.color.text_color)
        }


        binding.titEmail.doOnTextChanged { text, start, before, count ->
            binding.tilEmail.isEndIconVisible = validateEmail()
        }

        binding.titPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilPassword.setColorState(R.color.text_hint)
            else
                binding.tilPassword.setColorState(R.color.text_color)
        }


        binding.titPassword.doOnTextChanged { text, start, before, count ->
            binding.tilPassword.isEndIconVisible = validatePassword()
        }

        binding.btnSignIn.root.setOnClickListener {
            if (validateEmail() && validatePassword())
                viewModel.login(
                    binding.titEmail.text.toString(),
                    binding.titPassword.text.toString()
                )
        }
    }


    private fun validateEmail() = binding.titEmail.text!!.matches(emailPattern.toRegex())

    private fun validatePassword() = binding.titPassword.text!!.length > 8

    private fun observes() {
        viewModel.loginLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ResultHandler.Success -> {
                    d("success", it.toString())
                    if (binding.cbRemember.isChecked)
                        viewModel.saveSession(true)
                }
                is ResultHandler.Error -> d("success", it.toString())
                is ResultHandler.Loading -> binding.progress.showIf(it.loading)
            }
        })
    }
}