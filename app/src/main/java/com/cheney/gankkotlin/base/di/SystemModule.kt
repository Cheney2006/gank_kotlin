package com.cheney.gankkotlin.base.di

import android.content.Context
import com.cheney.gankkotlin.constants.PACKAGE_NAME
import com.cheney.gankkotlin.constants.VERSION_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class SystemModule {

    @Singleton
    @Named(PACKAGE_NAME)
    @Provides
    fun providePackageName(context: Context): String {
        return context.packageName
    }

    @Singleton
    @Named(VERSION_NAME)
    @Provides
    fun provideVersionName(context: Context,@Named(PACKAGE_NAME) packageName:String):String{
        val packageInfo=context.packageManager.getPackageInfo(packageName,0)
        return packageInfo.versionName
    }

}