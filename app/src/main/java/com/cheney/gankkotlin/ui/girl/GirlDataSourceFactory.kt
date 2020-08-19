package com.cheney.gankkotlin.ui.girl

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.disposables.CompositeDisposable


class GirlDataSourceFactory(
    private val gankRepository: GankRepository,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Gank>() {

    private val _dataSource = MutableLiveData<GirlDataSource>()

    val dataSource get() = _dataSource


    override fun create(): DataSource<Int, Gank> {
        val dataSource = GirlDataSource(compositeDisposable, gankRepository)
        _dataSource.postValue(dataSource)
        return dataSource
    }

    fun refresh() {
        _dataSource.value?.invalidate()
    }
}