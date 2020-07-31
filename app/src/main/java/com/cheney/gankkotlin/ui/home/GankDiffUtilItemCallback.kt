package com.cheney.gankkotlin.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.cheney.gankkotlin.bean.Gank

class GankDiffUtilItemCallback : DiffUtil.ItemCallback<Gank>() {
    override fun areItemsTheSame(oldItem: Gank, newItem: Gank): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Gank, newItem: Gank): Boolean {
        return oldItem == newItem
    }
}