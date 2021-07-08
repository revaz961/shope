package com.example.homework21.screen.bot_navigation.wall

import android.app.Dialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework21.adapter.PostAdapter
import com.example.homework21.databinding.ErrorDialogLayoutBinding
import com.example.homework21.databinding.WallFragmentBinding
import com.example.homework21.extension.hide
import com.example.homework21.extension.init
import com.example.homework21.extension.showIf
import com.example.homework21.network.ResultHandler
import com.example.homework21.screen.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WallFragment : BaseFragment<WallFragmentBinding>(WallFragmentBinding::inflate) {

    private val viewModel:WallViewModel by viewModels()
    private lateinit var adapter:PostAdapter

    override fun start() {
        observes()
        initRecycler()
    }

    private fun initRecycler(){
        adapter = PostAdapter()
        binding.rvPost.adapter = adapter
        binding.rvPost.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getPost()
    }

    private fun observes(){
        viewModel.liveData.observe(viewLifecycleOwner,{
            when(it){
                is ResultHandler.Success ->{
                    binding.progress.hide()
                    adapter.setItem(it.data!!)
                }
                is ResultHandler.Error ->{
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
                is ResultHandler.Loading ->{
                    binding.progress.showIf(it.loading)
                }
            }
        })
    }

}