package com.cheney.gankkotlin

import com.cheney.gankkotlin.base.di.DaggerAppComponent
import com.cheney.gankkotlin.base.di.NetworkModule
import com.cheney.gankkotlin.constants.BASE_URL
import com.cheney.gankkotlin.util.LifecycleCallbacks
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        LifecycleCallbacks.register(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(NetworkModule(BASE_URL), this)
    }

}