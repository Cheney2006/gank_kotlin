package com.cheney.gankkotlin.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.cheney.gankkotlin.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("glideUrl")
    fun loadImage(imageView: ImageView, imageUrl: String) {
        Glide.with(imageView).load(imageUrl).placeholder(R.drawable.image_placeholder)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("visibleGone")
    fun isShow(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

}