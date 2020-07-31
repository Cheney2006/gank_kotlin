package com.cheney.gankkotlin.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.base.di.ViewModelFactory
import com.cheney.gankkotlin.ui.home.HomeViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CategoryFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val categoryViewModel: CategoryViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_category, container, false)

        return root
    }
}