package com.example.homework21.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.homework21.model.MenuItem
import com.example.homework21.databinding.MenuItemBinding

typealias ClickListener = (id: Int) -> Unit

class MenuAdapter : BaseAdapter<MenuItem>() {

    lateinit var clickNavigate:ClickListener

    fun setItem(list: List<MenuItem>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<MenuItem, ViewBinding> {
        return MenuViewHolder(
            MenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    inner class MenuViewHolder(private val binding: MenuItemBinding) :
        BaseViewHolder<MenuItem, MenuItemBinding>(binding) {
        override fun bind(data: MenuItem) {
            binding.root.text = data.title
            binding.root.setOnClickListener {
                clickNavigate(data.id)
            }
        }
    }
}