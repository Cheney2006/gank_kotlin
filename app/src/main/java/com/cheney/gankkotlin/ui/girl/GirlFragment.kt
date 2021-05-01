package com.cheney.gankkotlin.ui.girl

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
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
        binding = FragmentGirlBinding.inflate(inflater,  container, false)
        viewModel.query()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarLayout.toolbar.title = getString(R.string.title_girl)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        adapter = GirlAdapter(GankDiffUtilItemCallback())
        binding.recyclerView.adapter = adapter

        viewModel.pagedList.observe(viewLifecycleOwner) { adapter.submitList(it) }
    }
}