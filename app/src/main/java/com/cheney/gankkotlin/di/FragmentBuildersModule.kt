package com.cheney.gankkotlin.di

import com.cheney.gankkotlin.ui.category.CategoryFragment
import com.cheney.gankkotlin.ui.home.HomeFragment
import com.cheney.gankkotlin.ui.notifications.NotificationsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributesDashboardFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun contributesNotificationsFragment(): NotificationsFragment

}