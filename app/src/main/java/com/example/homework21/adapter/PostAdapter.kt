package com.example.homework21.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.homework21.databinding.PostItemBinding
import com.example.homework21.extension.goneIf
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
        private lateinit var model:Post
        override fun bind(data: Post) {
            model = data

            initViewPagerAdapter()

            setData()
        }

        private fun initViewPagerAdapter(){
            binding.vpPost.adapter = PostImagesAdapter().apply {
                model.urls?.let { setItems(it) }
            }
            binding.vpPost.goneIf(model.urls.isNullOrEmpty())

            val size = model.urls?.size ?: 0
            binding.btnLeft.goneIf(size < 2)
            binding.btnRight.goneIf(size < 2)
            binding.tvCount.goneIf(size < 2)
            if(size >= 2)
                setCurrentPageIndex(1)

            binding.vpPost.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    setCurrentPageIndex(position + 1)
                }
            })
        }

        private fun setCurrentPageIndex(index: Int){
            binding.tvCount.text = "$index/${model.urls?.size}"
        }

        private fun setData(){
            binding.tvTitle.text = model.title
            binding.tvTag.text = model.tags
            binding.tvDescription.text = model.description
            binding.tvPrice.text = "$ ${model.price}"
        }

    }
}