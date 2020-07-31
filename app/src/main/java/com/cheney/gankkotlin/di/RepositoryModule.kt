package com.cheney.gankkotlin.di

import com.cheney.gankkotlin.api.GankService
import com.cheney.gankkotlin.base.di.NetworkModule
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGankService(retrofit: Retrofit): GankService {
        return retrofit.create(GankService::class.java)
    }
}