package com.cheney.gankkotlin.ui.girl

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

    private val _error = MutableLiveData<Throwable>()

    private val _canLoadMore = MutableLiveData<Boolean>()


    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Gank>) {
        _isLoading.postValue(true)
        gankRepository.getByCategoryType(
            CATEGORY_Girl,
            CATEGORY_Girl,
            params.requestedLoadSize,
            pageNo
        ).doOnSubscribe { disposable.add(it) }.subscribe({
            _isLoading.postValue(false)
            if (it.page_count > it.page) {

            }
        }, {})
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Gank>) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Gank>) {
        TODO("Not yet implemented")
    }

    override fun getKey(item: Gank): Int {
        return pageNo
    }
}