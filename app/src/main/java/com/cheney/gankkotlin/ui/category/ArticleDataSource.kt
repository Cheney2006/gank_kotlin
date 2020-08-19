package com.cheney.gankkotlin.ui.category

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.bean.Resource
import com.cheney.gankkotlin.constants.CATEGORY_ARTICLE
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.disposables.CompositeDisposable

class ArticleDataSource(
    private val compositeDisposable: CompositeDisposable,
    private val gankRepository: GankRepository,
    private val categoryType: CategoryType
) : ItemKeyedDataSource<Int, Gank>() {

    private var pageNo: Int = 1;

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading get() = _isLoading

    private val _error = MutableLiveData<Throwable>()
    val error get() = _error

    private val _canLoadMore = MutableLiveData<Boolean>()

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Gank>) {
        _isLoading.postValue(true)
        gankRepository.getByCategoryType(
            CATEGORY_ARTICLE,
            categoryType.type,
            params.requestedLoadSize,
            pageNo
        ).doOnSubscribe { disposable -> compositeDisposable.add(disposable) }
            .subscribe({ onLoadInitialSuccess(callback, it) }, { throwable: Throwable ->
                _isLoading.postValue(false)
                _error.postValue(throwable)
            })
    }

    private fun onLoadInitialSuccess(
        callback: LoadInitialCallback<Gank>,
        resource: Resource<List<Gank>>
    ) {
        _isLoading.postValue(false)
        if (resource.page_count!! > resource.page!!) {
            pageNo++
            _canLoadMore.postValue(true)
        } else {
            _canLoadMore.postValue(false)
        }
        resource.data?.let { callback.onResult(it) }
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Gank>) {
        if (!_canLoadMore.value!!) {
            return
        }
        gankRepository.getByCategoryType(
            CATEGORY_ARTICLE,
            categoryType.type, params.requestedLoadSize, pageNo
        ).doOnSubscribe { disposable ->
            compositeDisposable.add(disposable)
        }.subscribe({
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