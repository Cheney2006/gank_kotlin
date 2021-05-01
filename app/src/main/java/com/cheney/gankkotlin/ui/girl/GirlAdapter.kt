package com.cheney.gankkotlin.ui.girl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.databinding.ItemGirlBinding

class GirlAdapter(diffCallback: DiffUtil.ItemCallback<Gank>) :
    PagedListAdapter<Gank, GirlViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlViewHolder {
        return GirlViewHolder(
            ItemGirlBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GirlViewHolder, position: Int) {
        val data = getItem(position)
        Glide.with(holder.binding.girlIv.context).load(data?.getImageUrl(0))
            .placeholder(R.drawable.image_placeholder)
            .into(holder.binding.girlIv)
        holder.binding.descTv.text = data?.desc
    }
}

class GirlViewHolder(val binding: ItemGirlBinding) :
    RecyclerView.ViewHolder(binding.root)