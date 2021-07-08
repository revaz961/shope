package com.example.homework21.screen.home

import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework21.R
import com.example.homework21.adapter.MenuAdapter
import com.example.homework21.databinding.HomeFragmentBinding
import com.example.homework21.model.MenuItem
import com.example.homework21.screen.BaseFragment

class HomeFragment : BaseFragment<HomeFragmentBinding>(HomeFragmentBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun start() {
        initBottomNavigation()
        initDrawerMenu()
    }

    fun initBottomNavigation() {
        val navController =
            childFragmentManager
                .findFragmentById(R.id.bottom_nav_host_fragment) as NavHostFragment
        binding.botNavigation.setupWithNavController(navController.navController)
    }

    fun initDrawerMenu(){
        binding.rvMenuItems.adapter = MenuAdapter().apply {
            setItem(listOf(
                MenuItem(R.id.action_homeFragment_to_proofileFragment,getString(R.string.my_profile)),
                MenuItem(R.id.action_homeFragment_to_postFragment,getString(R.string.my_post)),
                MenuItem(R.id.action_homeFragment_to_proofileFragment,getString(R.string.my_profile)),
                MenuItem(R.id.action_global_signInFragment,getString(R.string.log_out))
            ))

            clickNavigate = {
                findNavController().navigate(it)
            }
        }

        binding.rvMenuItems.layoutManager = LinearLayoutManager(requireContext())

        binding.toolbar.btnDrawer.setOnClickListener {
            binding.root.openDrawer(GravityCompat.END)
        }
    }
}