package com.example.homework21.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.homework21.databinding.PostImagesItemBinding
import com.example.homework21.extension.load

class PostImagesAdapter : BaseAdapter<String>() {

    fun setItems(list:List<String>){
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<String, ViewBinding> {
        return PostImagesViewHolder(
            PostImagesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class PostImagesViewHolder(private val binding: PostImagesItemBinding) :
        BaseViewHolder<String, PostImagesItemBinding>(binding) {
        override fun bind(data: String) {
            binding.ivPost.load(data)
            binding.tvCount.text = "${adapterPosition + 1}/${items.size}"
        }
    }
}