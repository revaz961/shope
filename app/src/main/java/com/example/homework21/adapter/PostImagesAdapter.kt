package com.example.homework21.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.homework21.databinding.PostImagesItemBinding
import com.example.homework21.extension.load
import com.example.homework21.model.Post

class PostImagesAdapter : BaseAdapter<Post.Url>() {

    fun setItems(list:List<Post.Url>){
        items.addAll(list)
        notifyDataSetChanged()
    }



    inner class PostImagesViewHolder(private val binding: PostImagesItemBinding) :
        BaseViewHolder<Post.Url, PostImagesItemBinding>(binding) {
        override fun bind(data: Post.Url) {
            binding.ivPost.load(data.url)
//            binding.tvCount.text = "${adapterPosition + 1}/${items.size}"
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Post.Url, ViewBinding> {
        return PostImagesViewHolder(
            PostImagesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}