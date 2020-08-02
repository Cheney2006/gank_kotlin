package com.cheney.gankkotlin.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ArticleViewModel @Inject constructor(private val gankRepository: GankRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val isLoadingLiveData = MutableLiveData<Boolean>()
    private val errorLiveData = MutableLiveData<Throwable>()

//    private lateinit var factor
    private lateinit var pagedLiveData: MutableLiveData<PagedList<Gank>>


    val isLoading get() = isLoadingLiveData


    fun setCategoryType(type: CategoryType) {

    }

    fun refresh(){

    }


}