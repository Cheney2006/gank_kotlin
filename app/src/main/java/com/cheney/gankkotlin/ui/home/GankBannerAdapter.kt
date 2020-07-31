package com.cheney.gankkotlin.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.bean.GankBanner
import com.cheney.gankkotlin.databinding.ItemBannerBinding
import com.youth.banner.adapter.BannerAdapter

class GankBannerAdapter(
    datas: List<GankBanner>,
    private val onItemClickListener: OnItemClickListener
) :
    BannerAdapter<GankBanner, GankBannerAdapter.BannerViewHolder>(datas) {


    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = DataBindingUtil.inflate<ItemBannerBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_banner,
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun onBindView(
        holder: BannerViewHolder,
        data: GankBanner,
        position: Int,
        size: Int
    ) {
        holder.binding.gankBanner = data
        holder.binding.itemClick = onItemClickListener
    }

    class BannerViewHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(gankBanner: GankBanner)
    }
}