package com.example.homework21.ui.feed

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework21.adapter.PostAdapter
import com.example.homework21.databinding.FeedFragmentBinding
import com.example.homework21.network.ResultHandler
import com.example.homework21.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FeedFragment : BaseFragment<FeedFragmentBinding>(FeedFragmentBinding::inflate) {

    private val viewModel:FeedViewModel by viewModels()
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
                    adapter.setItem(it.data!!)
                }
                is ResultHandler.Error ->{
//                    adapter.setItem(it.data!!)
                }
                is ResultHandler.Loading ->{
//                    adapter.setItem(it.data!!)
                }
            }
        })
    }

}