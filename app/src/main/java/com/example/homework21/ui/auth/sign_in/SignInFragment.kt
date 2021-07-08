package com.example.homework21.ui.auth.sign_in

import android.app.Dialog
import android.util.Log.d
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.homework21.R
import com.example.homework21.databinding.ErrorDialogLayoutBinding
import com.example.homework21.databinding.SignInFragmentBinding
import com.example.homework21.extension.*
import com.example.homework21.network.ResultHandler
import com.example.homework21.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<SignInFragmentBinding>(
    SignInFragmentBinding::inflate
) {

    private val viewModel: SignInViewModel by viewModels()

    override fun start() {
        initView()
        observes()
        setListeners()
    }


    private fun initView() {
        val email = arguments?.getString("email","")
        val password = arguments?.getString("password","")
        binding.titEmail.setText(email)
        binding.titPassword.setText(password)

        binding.tilEmail.isEndIconVisible = false
        binding.tilPassword.isEndIconVisible = false

        binding.btnSignIn.root.text = getString(R.string.sign_in)

        binding.tvSignUp.setSpannedString(
            arrayOf(
                getString(R.string.new_user),
                getString(R.string.sign_up),
                getString(R.string.here)
            ),
            arrayOf(R.color.text_color, R.color.text_hint, R.color.text_color)
        )

        binding.tvSignUp.isClickable = true
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

        binding.btnSignIn.root.setOnClickListener {
            val email = binding.titEmail.text.toString()
            if (email.isEmail() && validatePassword())
                viewModel.login(
                    email,
                    binding.titPassword.text.toString(),
                    binding.cbRemember.isChecked
                )
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun validatePassword() = binding.titPassword.text!!.length >= 6

    private fun observes() {
        viewModel.loginLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is ResultHandler.Success -> {
                    findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                    binding.progress.hide()
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