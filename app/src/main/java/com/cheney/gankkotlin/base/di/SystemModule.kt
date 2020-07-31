package com.cheney.gankkotlin.base.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SystemModule {
    @Singleton
    @Provides
    fun providePackageName(context: Context): String {
        return context.packageName
    }

}