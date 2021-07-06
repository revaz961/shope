package com.example.homework21.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.homework21.databinding.PostItemBinding
import com.example.homework21.model.Post

class PostAdapter : BaseAdapter<Post>() {

    fun setItem(list:List<Post>){
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Post, ViewBinding> {
        return PostViewHolder(
            PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class PostViewHolder(private val binding: PostItemBinding) :
        BaseViewHolder<Post, PostItemBinding>(binding) {
        override fun bind(data: Post) {

            binding.tvTitle.text = data.title
            binding.tvTag.text = data.tags
            binding.tvDescription.text = data.description
            binding.tvPrice.text = "$ ${data.price}"
        }

    }
}