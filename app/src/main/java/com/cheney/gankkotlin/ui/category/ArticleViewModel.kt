package com.cheney.gankkotlin.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ArticleViewModel @Inject constructor(private val gankRepository: GankRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

//        private var _isLoadingLiveData: LiveData<Boolean> = MutableLiveData<Boolean>()
    private lateinit var _isLoadingLiveData: LiveData<Boolean>

    private lateinit var _errorLiveData: LiveData<Throwable>

    private lateinit var factor: ArticleDataSourceFactory

    private lateinit var _pagedLiveData: LiveData<PagedList<Gank>>


    val isLoading get() = _isLoadingLiveData

    val pagedLiveData get() = _pagedLiveData


    fun queryByCategoryType(type: CategoryType) {
        factor = ArticleDataSourceFactory(
            gankRepository,
            categoryType = type,
            compositeDisposable = compositeDisposable
        )

        initPaging()
    }

    private fun initPaging() {
        val config = Config(
            pageSize = 10,
            prefetchDistance = 2,
            enablePlaceholders = false,
            initialLoadSizeHint = 10
        )
//        pagedLiveData = LivePagedListBuilder(factor, config).build()
        _pagedLiveData = factor.toLiveData(config = config)

//        _isLoadingLiveData = Transformations.switchMap(factor.dataSource,{input:ArticleDataSource->input.isLoadingLiveData})
        _isLoadingLiveData = Transformations.switchMap(factor.dataSource) {
            it.isLoadingLiveData
        }

        _errorLiveData =
            Transformations.switchMap(factor.dataSource, ArticleDataSource::error)

    }

    fun refresh() {
        factor.refresh()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }


}