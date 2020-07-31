package com.cheney.gankkotlin.base.di

import android.app.Application
import com.cheney.gankkotlin.di.MainActivityModule
import com.cheney.gankkotlin.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityModule::class, ViewModelModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(
            networkModule: NetworkModule,
            @BindsInstance application: Application//这里只能是application
        ): AppComponent
    }

}