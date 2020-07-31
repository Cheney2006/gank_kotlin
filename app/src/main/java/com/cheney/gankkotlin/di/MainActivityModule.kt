package com.cheney.gankkotlin.di

import com.cheney.gankkotlin.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributesMainActivity(): MainActivity

}