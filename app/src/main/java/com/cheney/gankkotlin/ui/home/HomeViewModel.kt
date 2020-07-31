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

    private val _isLoading = MutableLiveData<Boolean>()

    private val _banners = MutableLiveData<List<GankBanner>>()

    private val _ganks = MutableLiveData<List<Gank>>()

    private val _error = MutableLiveData<Throwable>()

    val isLoading: MutableLiveData<Boolean> get() = _isLoading;

    val error: MutableLiveData<Throwable> get() = _error

    val banner get() = _banners

    val ganks get() = _ganks


    @SuppressLint("CheckResult")
    fun query() {
        _isLoading.postValue(true)
        Single.zip(
            gankRepository.getBanner(),
            gankRepository.getHot(),
            BiFunction<List<GankBanner>?, List<Gank>?, Any?> { bannerList, gankList ->
                _banners.postValue(bannerList)
                _ganks.postValue(gankList)
            }).doOnSubscribe { disposable: Disposable -> compositeDisposable.add(disposable) }
            .subscribe({
                _isLoading.postValue(false)
            }, {
                _isLoading.postValue(false)
                _error.postValue(it)
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
