package com.cheney.gankkotlin.ui.category

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cheney.gankkotlin.bean.CategoryType

class CategoryPagerAdapter(fragment: Fragment, private val categories: List<CategoryType>) :
    FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int {
        return categories.size
    }


    override fun createFragment(position: Int): Fragment {
        return ArticleFragment.newInstance(categories[position])
    }


}