package com.cheney.gankkotlin.ui.home

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheney.gankkotlin.bean.Gank
import com.cheney.gankkotlin.bean.GankBanner
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val gankRepository: GankRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val isLoadingLiveData = MutableLiveData<Boolean>()

    private val bannersLiveData = MutableLiveData<List<GankBanner>>()

    private val ganksLiveData = MutableLiveData<List<Gank>>()

    private val errorLiveData = MutableLiveData<Throwable>()

    val isLoading: MutableLiveData<Boolean> get() = isLoadingLiveData;

    val error: MutableLiveData<Throwable> get() = errorLiveData

    val banner get() = bannersLiveData

    val ganks get() = ganksLiveData


    @SuppressLint("CheckResult")
    fun query() {
        isLoadingLiveData.postValue(true)
        Single.zip(
            gankRepository.getBanner(),
            gankRepository.getHot(),
            BiFunction<List<GankBanner>?, List<Gank>?, Any?> { bannerList, gankList ->
                bannersLiveData.postValue(bannerList)
                ganksLiveData.postValue(gankList)
            }).doOnSubscribe { disposable: Disposable -> compositeDisposable.add(disposable) }
            .subscribe({
                isLoadingLiveData.postValue(false)
            }, {
                isLoadingLiveData.postValue(false)
                errorLiveData.postValue(it)
            })
//        gankRepository.getBanner()
//            .doOnSubscribe { disposable: Disposable -> compositeDisposable.add(disposable) }
//            .subscribe({
//                _isLoading.postValue(false)
//                _banners.postValue(it)
//            }, { throwable: Throwable ->
//                _isLoading.postValue(false)
//                _error.postValue(throwable)
//            })

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
