package com.example.homework21.screen.profile

import androidx.fragment.app.viewModels
import com.example.homework21.R
import com.example.homework21.databinding.ProfileFragmentBinding
import com.example.homework21.extension.setColorState
import com.example.homework21.screen.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileFragmentBinding>(ProfileFragmentBinding::inflate) {

    private val viewModel:ProfileViewModel by viewModels()

    override fun start() {
        initView()
        setListeners()
    }

    private fun initView(){
        binding.tilAddress.isEndIconVisible = false
        binding.tilCardHolderName.isEndIconVisible = false
        binding.tilCardNumber.isEndIconVisible = false
        binding.tilExpiryDate.isEndIconVisible = false
        binding.tilSecurityCode.isEndIconVisible = false
        binding.tilFloorApartment.isEndIconVisible = false
    }

    private fun setListeners() {
        binding.editAddress.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilAddress.setColorState(R.color.text_hint)
            else
                binding.tilAddress.setColorState(R.color.text_color)
        }

        binding.editCardHolderName.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilCardHolderName.setColorState(R.color.text_hint)
            else
                binding.tilCardHolderName.setColorState(R.color.text_color)
        }

        binding.editCardNumber.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilCardNumber.setColorState(R.color.text_hint)
            else
                binding.tilCardNumber.setColorState(R.color.text_color)
        }

        binding.editExpiryDate.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilExpiryDate.setColorState(R.color.text_hint)
            else
                binding.tilExpiryDate.setColorState(R.color.text_color)
        }

        binding.editSecurityCode.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilSecurityCode.setColorState(R.color.text_hint)
            else
                binding.tilSecurityCode.setColorState(R.color.text_color)
        }

        binding.editFloorApartment.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
                binding.tilFloorApartment.setColorState(R.color.text_hint)
            else
                binding.tilFloorApartment.setColorState(R.color.text_color)
        }
    }
}