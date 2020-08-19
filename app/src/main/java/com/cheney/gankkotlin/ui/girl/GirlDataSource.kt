package com.cheney.gankkotlin.ui.girl

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.constants.CATEGORY_Girl
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.disposables.CompositeDisposable

class GirlDataSource constructor(
    private val disposable: CompositeDisposable,
    private val gankRepository: GankRepository
) : ItemKeyedDataSource<Int, Gank>() {

    private var pageNo = 1

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading get() = _isLoading

    private val _error = MutableLiveData<Throwable>()
    val error get() = _error

    private val _canLoadMore = MutableLiveData<Boolean>()


    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Gank>) {
        _isLoading.postValue(true)
        gankRepository.getByCategoryType(
            CATEGORY_Girl,
            CATEGORY_Girl,
            params.requestedLoadSize,
            pageNo
        ).doOnSubscribe { disposable.add(it) }.subscribe({
            _isLoading.postValue(false)
            if (it.page_count!! > it.page!!) {
                pageNo++
                _canLoadMore.postValue(true)
            } else {
                _canLoadMore.postValue(false)
            }
            it.data?.let { data -> callback.onResult(data) }
        }, {
            _isLoading.postValue(false)
            _error.postValue(it)
        })
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Gank>) {
//        if (!(_canLoadMore.value ?: false)) {
        if (_canLoadMore.value != true) {
            return
        }
        gankRepository.getByCategoryType(
            CATEGORY_Girl,
            CATEGORY_Girl, params.requestedLoadSize, pageNo
        ).doOnSubscribe { disposable.add(it) }.subscribe({
            if (it.page_count!! > it.page!!) {
                pageNo++
                _canLoadMore.postValue(true)
            } else {
                _canLoadMore.postValue(false)
            }
            it.data?.let { data -> callback.onResult(data) }
        }, {
            _error.postValue(it)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Gank>) {
    }

    override fun getKey(item: Gank): Int {
        return pageNo
    }
}