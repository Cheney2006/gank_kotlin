package com.cheney.gankkotlin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.databinding.ItemGankBinding

class HomeAdapter(
    diffCallback: DiffUtil.ItemCallback<Gank>,
    private val onItemClickListener: (Gank) -> Unit
) :
    ListAdapter<Gank, GankViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GankViewHolder {
        return GankViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_gank,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GankViewHolder, position: Int) {
        holder.binding.gank = getItem(position)
        holder.binding.root.setOnClickListener {
//            val onItemClickListener=::onItemClickListener
//            onItemClickListener(getItem(position))
            onItemClickListener(getItem(position))
//            onItemClickListener.invoke(getItem(position))
        }
        holder.binding.executePendingBindings()
    }

}

class GankViewHolder(val binding: ItemGankBinding) : RecyclerView.ViewHolder(binding.root)
