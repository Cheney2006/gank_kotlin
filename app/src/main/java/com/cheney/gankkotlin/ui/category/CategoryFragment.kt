package com.cheney.gankkotlin.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.base.di.ViewModelFactory
import com.cheney.gankkotlin.databinding.FragmentCategoryBinding
import com.cheney.gankkotlin.ui.home.HomeViewModel
import com.cheney.gankkotlin.util.autoCleared
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class CategoryFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val categoryViewModel: CategoryViewModel by viewModels {
        viewModelFactory
    }

    private var binding by autoCleared<FragmentCategoryBinding>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);

        binding.lifecycleOwner = viewLifecycleOwner;
        binding.viewModel = categoryViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoryViewModel.query()

        setToolbar()

        setPager()

    }

    private fun setPager() {

    }

    private fun setToolbar() {
        binding.toolbarLayout.toolbar.title = getString(R.string.title_category)
    }
}