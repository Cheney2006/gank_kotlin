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
import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.databinding.FragmentCategoryBinding
import com.cheney.gankkotlin.ui.home.HomeViewModel
import com.cheney.gankkotlin.util.autoCleared
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import java.sql.Types
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
        categoryViewModel.categories.observe(
            viewLifecycleOwner,
            Observer(this::initPager)
        )
    }

    private fun initPager(categoryTypes: List<CategoryType>) {
        binding.pager.adapter = CategoryPagerAdapter(this, categoryTypes)
//           TabLayoutMediator(binding.tabLayout,binding.pager, object : TabLayoutMediator.TabConfigurationStrategy{
//               override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
//                   tab.text=it[position].title
//               }
//           }).attach()
        TabLayoutMediator(
            binding.tabLayout,
            binding.pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                tab.text = categoryTypes[position].title
            }).attach()
    }

    private fun setToolbar() {
        binding.toolbarLayout.toolbar.title = getString(R.string.title_category)
        binding.toolbarLayout.toolbar.elevation = 0F
    }
}