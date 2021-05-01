package com.cheney.gankkotlin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.bean.GankBanner
import com.cheney.gankkotlin.databinding.ItemBannerBinding
import com.youth.banner.adapter.BannerAdapter

class GankBannerAdapter(
    datas: List<GankBanner>,
    private val onItemClickListener: (GankBanner) -> Unit
) :
    BannerAdapter<GankBanner, GankBannerAdapter.BannerViewHolder>(datas) {


    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            ItemBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: GankBanner,
        position: Int,
        size: Int
    ) {
        holder.binding.titleTv.text = data.title
        Glide.with(holder.binding.coverIv.context).load(data.image)
            .placeholder(R.drawable.image_placeholder)
            .into(holder.binding.coverIv)
        holder.binding.root.setOnClickListener {
            onItemClickListener.invoke(data)
        }
    }

    class BannerViewHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}