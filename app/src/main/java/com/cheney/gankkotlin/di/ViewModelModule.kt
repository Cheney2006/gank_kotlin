package com.cheney.gankkotlin.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cheney.gankkotlin.base.di.ViewModelFactory
import com.cheney.gankkotlin.base.di.ViewModelKey
import com.cheney.gankkotlin.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel


}