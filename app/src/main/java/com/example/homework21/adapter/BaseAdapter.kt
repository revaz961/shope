package com.example.homework21.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseAdapter<T>() :
    RecyclerView.Adapter<BaseViewHolder<T,ViewBinding>>() {

    protected val items = mutableListOf<T>()

    override fun onBindViewHolder(holder: BaseViewHolder<T,ViewBinding>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}