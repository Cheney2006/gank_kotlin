package com.cheney.gankkotlin.base.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.cheney.gankkotlin.di.RepositoryModule
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [SystemModule::class, CommonModule::class, RepositoryModule::class])
abstract class AppModule {
    @Singleton
    @Binds
    abstract fun provideContext(application: Application): Context

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}