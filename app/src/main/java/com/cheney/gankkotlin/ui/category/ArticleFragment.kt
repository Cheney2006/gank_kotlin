package com.cheney.gankkotlin.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.databinding.FragmentAritcleBinding
import com.cheney.gankkotlin.ui.home.GankDiffUtilItemCallback
import com.cheney.gankkotlin.util.autoCleared
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ArticleFragment(private val categoryType: CategoryType) : DaggerFragment() {

    companion object {
        fun newInstance(categoryType: CategoryType) = ArticleFragment(categoryType)

//        fun create(categoryType: CategoryType)=ArticleFragment().apply {
//            arguments=Bundle().apply { putString() }
//        }
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by viewModels<ArticleViewModel> { factory }

    private var binding by autoCleared<FragmentAritcleBinding>()

    private var articleAdapter by autoCleared<ArticleAdapter>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentAritcleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.queryByCategoryType(categoryType)
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        setAdapter()

    }

    private fun setAdapter() {
        val adapter = ArticleAdapter(GankDiffUtilItemCallback()) {
            findNavController().navigate(CategoryFragmentDirections.actionGlobalWebViewFragment(it.title,it.url))
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        articleAdapter = adapter

        viewModel.pagedLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })


    }


}