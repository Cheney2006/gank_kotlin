package com.cheney.gankkotlin.di

import com.cheney.gankkotlin.ui.category.ArticleFragment
import com.cheney.gankkotlin.ui.category.CategoryFragment
import com.cheney.gankkotlin.ui.home.HomeFragment
import com.cheney.gankkotlin.ui.girl.GirlFragment
import com.cheney.gankkotlin.ui.login.LoginFragment
import com.cheney.gankkotlin.ui.my.MyFragment
import com.cheney.gankkotlin.ui.web.WebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun contributesArticleFragment(): ArticleFragment

    @ContributesAndroidInjector
    abstract fun contributesNotificationsFragment(): GirlFragment

    @ContributesAndroidInjector
    abstract fun contributesMyFragment(): MyFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeWebViewFragment():WebViewFragment

}