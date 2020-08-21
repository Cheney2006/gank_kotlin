package com.cheney.gankkotlin.ui.girl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.databinding.ItemGirlBinding

class GirlAdapter(diffCallback: DiffUtil.ItemCallback<Gank>) :
    PagedListAdapter<Gank, GirlViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlViewHolder {
        return GirlViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_girl,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GirlViewHolder, position: Int) {
        holder.binding.gank = getItem(position)
        holder.binding.executePendingBindings()
    }
}

class GirlViewHolder(val binding: ItemGirlBinding) :
    RecyclerView.ViewHolder(binding.root)