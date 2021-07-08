package com.example.homework21.screen.auth.sign_up

import android.app.Dialog
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homework21.R
import com.example.homework21.databinding.ErrorDialogLayoutBinding
import com.example.homework21.databinding.SignUpFragmentBinding
import com.example.homework21.extension.*
import com.example.homework21.network.ResultHandler
import com.example.homework21.screen.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<SignUpFragmentBinding>(SignUpFragmentBinding::inflate) {

    private val viewModel: SignUpViewModel by viewModels()

    override fun start() {
        initView()
        observes()
    }

    private fun initView() {
        binding.tilEmail.isEndIconVisible = false
        binding.tilPassword.isEndIconVisible = false
        binding.tilRepeatPassword.isEndIconVisible = false
        binding.tilFullName.isEndIconVisible = false

        binding.btnSignUp.root.text = getString(R.string.sign_up)

        binding.tvLogIn.setSpannedString(
            arrayOf(
                getString(R.string.already_a_member),
                getString(R.string.log_in)
            ),
            arrayOf(R.color.text_color, R.color.text_hint)
        )

        binding.tvLogIn.isClickable = true

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
            binding.tilEmail.isEndIconVisible = binding.titEmail.text.toString().isEmail()
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

        binding.titRepeatPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilRepeatPassword.setColorState(R.color.text_hint)
            else
                binding.tilRepeatPassword.setColorState(R.color.text_color)
        }


        binding.titRepeatPassword.doOnTextChanged { text, start, before, count ->
            binding.tilRepeatPassword.isEndIconVisible = validatePassword()
        }

        binding.titFullName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilFullName.setColorState(R.color.text_hint)
            else
                binding.tilFullName.setColorState(R.color.text_color)
        }


        binding.titFullName.doOnTextChanged { text, start, before, count ->
            binding.tilFullName.isEndIconVisible = !text.isNullOrBlank()
        }

        binding.btnSignUp.root.setOnClickListener {
            val email = binding.titEmail.text.toString()
            val fullName = binding.titFullName.text.toString()
            val password = binding.titPassword.text.toString()

            if (email.isEmail() && fullName.isNotBlank() && validatePassword())
                viewModel.register(
                    email,
                    password,
                    fullName
                )
        }

        binding.tvLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    private fun validatePassword() =
        binding.titPassword.text!!.length >= 6
                && binding.titPassword.text.toString() == binding.titRepeatPassword.text.toString()

    private fun observes() {
        viewModel.registerLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ResultHandler.Success -> {
                    binding.progress.hide()
                    val email = binding.titEmail.text.toString()
                    val password = binding.titPassword.text.toString()
                    findNavController().navigate(
                        R.id.action_signUpFragment_to_signInFragment,
                        bundleOf("email" to email, "password" to password)
                    )
                }
                is ResultHandler.Error -> {
                    binding.progress.hide()
                    val dialog = Dialog(requireContext())
                    val dialogBinding = ErrorDialogLayoutBinding.inflate(layoutInflater)
                    dialog.init(dialogBinding.root)
                    dialogBinding.tvDescription.text = it.message
                    dialogBinding.btnClose.setOnClickListener {
                        dialog.cancel()
                    }
                    dialog.show()
                }
                is ResultHandler.Loading -> binding.progress.showIf(it.loading)
            }
        })
    }
}