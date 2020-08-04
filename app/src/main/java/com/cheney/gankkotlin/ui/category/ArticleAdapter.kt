package com.cheney.gankkotlin.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.ui.home.HomeAdapter

class ArticleAdapter(
    diffCallback: DiffUtil.ItemCallback<Gank>,
    private val onItemClickListener: (Gank) -> Unit
) :
    PagedListAdapter<Gank, HomeAdapter.GankViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.GankViewHolder {
        return HomeAdapter.GankViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_gank,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeAdapter.GankViewHolder, position: Int) {
        holder.binding.gank = getItem(position)
        holder.binding.root.setOnClickListener {
            getItem(position)?.let { onItemClickListener.invoke(it) }
        }
        holder.binding.executePendingBindings()
    }


}