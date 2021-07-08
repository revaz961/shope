package com.example.homework21.ui.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.homework21.R
import com.example.homework21.databinding.HomeFragmentBinding
import com.example.homework21.ui.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun start() {
        initBottomNavigation()
    }

    fun initBottomNavigation() {
        val navController =
            childFragmentManager
                .findFragmentById(R.id.bottom_nav_host_fragment) as NavHostFragment
        binding.botNavigation.setupWithNavController(navController.navController)
    }

}