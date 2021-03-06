package com.cheney.gankkotlin.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.disposables.CompositeDisposable


class ArticleDataSourceFactory(
    private val gankRepository: GankRepository,
    private val categoryType: CategoryType,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Gank>() {

    private val _dataSource = MutableLiveData<ArticleDataSource>()

    val dataSource get() = _dataSource

    override fun create(): DataSource<Int, Gank> {
        val dataSource = ArticleDataSource(compositeDisposable, gankRepository, categoryType)
        _dataSource.postValue(dataSource)
        return dataSource
    }

    fun refresh() {
        dataSource.value?.invalidate()
    }

}