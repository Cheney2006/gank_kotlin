package com.cheney.gankkotlin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
            ItemGankBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GankViewHolder, position: Int) {
        val data = getItem(position)
        Glide.with(holder.binding.coverIv.context).load(data.getImageUrl(0))
            .placeholder(R.drawable.image_placeholder)
            .into(holder.binding.coverIv)
        holder.binding.typeTv.text = data.type
        holder.binding.titleTv.text = data.title
        holder.binding.descTv.text = data.desc
        holder.binding.authorTv.text = data.author
        holder.binding.viewsTv.text = "${data.views}"
        holder.binding.publishedAtTv.text = data.publishedAt()
        holder.binding.root.setOnClickListener {
//            val onItemClickListener=::onItemClickListener
//            onItemClickListener(getItem(position))
            onItemClickListener(getItem(position))
//            onItemClickListener.invoke(getItem(position))
        }
    }

}

class GankViewHolder(val binding: ItemGankBinding) : RecyclerView.ViewHolder(binding.root)
