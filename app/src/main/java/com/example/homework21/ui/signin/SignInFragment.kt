package com.example.homework21.ui.signin

import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import androidx.fragment.app.viewModels
import com.example.homework21.R
import com.example.homework21.databinding.SignInFragmentBinding
import com.example.homework21.extension.setColorState
import com.example.homework21.extension.setIconEnd
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
        binding.titEmail.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilEmail.setColorState(R.color.text_hint)
            else
                binding.tilEmail.setColorState(R.color.text_color)
        }

        binding.titEmail.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(validateEmail())
                    binding.tilEmail.setIconEnd(R.drawable.baseline_check_circle_24)
                else
                    binding.tilEmail.endIconDrawable = null
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.titPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilPassword.setColorState(R.color.text_hint)
            else
                binding.tilPassword.setColorState(R.color.text_color)
        }


        binding.titPassword.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(validatePassword())
                    binding.tilPassword.setIconEnd(R.drawable.baseline_check_circle_24)
                else
                    binding.tilPassword.endIconDrawable = null
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.btnSignIn.root.text = getString(R.string.sign_in)
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
            d("userIs", it.toString())
        })
        viewModel.registerLiveData.observe(viewLifecycleOwner, {

        })
    }

}