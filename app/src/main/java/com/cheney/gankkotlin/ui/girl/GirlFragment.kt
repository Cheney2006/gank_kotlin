package com.cheney.gankkotlin.ui.girl

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
import androidx.lifecycle.observe
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.databinding.FragmentGirlBinding
import com.cheney.gankkotlin.ui.home.GankDiffUtilItemCallback
import com.cheney.gankkotlin.util.autoCleared
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GirlFragment : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<GirlViewModel> { factory }

    private var binding by autoCleared<FragmentGirlBinding>()

    private var adapter by autoCleared<GirlAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_girl, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        viewModel.query()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarLayout.toolbar.title = getString(R.string.title_girl)

        adapter = GirlAdapter(GankDiffUtilItemCallback())
        binding.recyclerView.adapter = adapter

        viewModel.pagedList.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }
}