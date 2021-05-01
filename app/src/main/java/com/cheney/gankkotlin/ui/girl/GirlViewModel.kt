package com.cheney.gankkotlin.ui.girl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GirlViewModel @Inject constructor(
    gankRepository: GankRepository
) : ViewModel() {

    private var config = Config(
        pageSize = 10,
        prefetchDistance = 2,
        enablePlaceholders = false,
        initialLoadSizeHint = 10
    )
    private val compositeDisposable = CompositeDisposable()

    private var _isLoading: LiveData<Boolean> = MutableLiveData<Boolean>()

    val isLoading get() = _isLoading

    private lateinit var _error: LiveData<Throwable>

    val error get() = _error

    private var _factory: GirlDataSourceFactory

    private lateinit var _pagedList: LiveData<PagedList<Gank>>
    val pagedList get() = _pagedList


    fun query() {
        _pagedList = _factory.toLiveData(config = config)

        _isLoading = Transformations.switchMap(_factory.dataSource) { it.isLoading }

        _error = Transformations.switchMap(_factory.dataSource) { it.error }

    }

    init {
        _factory = GirlDataSourceFactory(gankRepository, compositeDisposable)
    }

    fun refresh() {
        _factory.refresh()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}