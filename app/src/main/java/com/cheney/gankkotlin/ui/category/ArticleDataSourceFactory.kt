package com.cheney.gankkotlin.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.DataSource.Factory
import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.disposables.CompositeDisposable


class ArticleDataSourceFactory (
    private val gankRepository: GankRepository,
    private val categoryType: CategoryType
) : Factory<Int, Gank>() {

    private val compositeDisposable = CompositeDisposable()
    private val dataSourceLiveData = MutableLiveData<ArticleDataSource>()

    override fun create(): DataSource<Int, Gank> {
        TODO("Not yet implemented")
    }
}