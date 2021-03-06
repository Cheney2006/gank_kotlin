package com.cheney.gankkotlin.ui.home

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
import com.cheney.gankkotlin.R
import com.cheney.gankkotlin.bean.GankBanner
import com.cheney.gankkotlin.constants.PACKAGE_NAME
import com.cheney.gankkotlin.databinding.FragmentHomeBinding
import com.cheney.gankkotlin.util.autoCleared
import com.google.android.material.appbar.AppBarLayout
import com.youth.banner.config.IndicatorConfig
import com.youth.banner.indicator.CircleIndicator
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.abs

class HomeFragment : DaggerFragment() {

    @Inject
    @Named(PACKAGE_NAME)
    lateinit var packageName: String

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val homeViewModel: HomeViewModel by viewModels {
        viewModelFactory
    }

    private var binding by autoCleared<FragmentHomeBinding>()

    private var homeAdapter by autoCleared<HomeAdapter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        homeViewModel.query()

        setToolbar()

        homeViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }
        binding.swipeRefresh.setOnRefreshListener {
            homeViewModel.query()
        }

        setBanner()

        setAdapter()
    }


    private fun setAdapter() {

        val adapter = HomeAdapter(GankDiffUtilItemCallback()) {
            findNavController().navigate(HomeFragmentDirections.actionGlobalWebViewFragment(it.title,it.url))
        }

//        val adapter = HomeAdapter(GankDiffUtilItemCallback(), fun (gank: Gank) {
//            println(gank._id)
//        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        homeAdapter = adapter

        homeViewModel.ganks.observe(viewLifecycleOwner, Observer { result ->
            adapter.submitList(result)
        })


    }

    private fun setToolbar() {
        binding.appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset -> //当滑动到顶部的时候开启
            binding.swipeRefresh.isEnabled = verticalOffset >= 0

            if (abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                binding.toolbarLayout.toolbar.visibility = View.VISIBLE
                binding.collapsingToolbarLayout.title = getString(R.string.title_home)
            } else {
                binding.toolbarLayout.toolbar.visibility = View.GONE
                binding.collapsingToolbarLayout.title = ""
            }
        })
    }

    private fun setBanner() {
//        homeViewModel.banner.observe(viewLifecycleOwner, Observer { gankBanners -> {
//            binding.banner.addBannerLifecycleObserver(viewLifecycleOwner).setAdapter()
//        } })

//        homeViewModel.banner.observe(viewLifecycleOwner, object : Observer<List<GankBanner>> {
//            override fun onChanged(t: List<GankBanner>?) {
//
//            }
//        })

        homeViewModel.banner.observe(viewLifecycleOwner, Observer { initBanner(it) })
    }

    private fun initBanner(gankBanners: List<GankBanner>) {
//        val adapter=GankBannerAdapter(gankBanners,object : GankBannerAdapter.OnItemClickListener {
//            override fun onItemClick(gankBanner: GankBanner) {
//            }
//        })
        val adapter = GankBannerAdapter(gankBanners) {
            findNavController().navigate(HomeFragmentDirections.actionGlobalWebViewFragment(it.title,it.url))
        }

        binding.banner.addBannerLifecycleObserver(viewLifecycleOwner).adapter = adapter
        binding.banner.indicator = CircleIndicator(requireContext())
        binding.banner.setIndicatorGravity(IndicatorConfig.Direction.RIGHT)
        binding.banner.start()

    }

}