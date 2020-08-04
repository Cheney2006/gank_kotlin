package com.cheney.gankkotlin.ui.category

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheney.gankkotlin.bean.CategoryType
import com.cheney.gankkotlin.constants.CATEGORY_ARTICLE
import com.cheney.gankkotlin.repository.GankRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CategoryViewModel @Inject constructor(private val gankRepository: GankRepository) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _categories = MutableLiveData<List<CategoryType>>()
    private val _error = MutableLiveData<Throwable>()

    val categories get() = _categories

    val error get() = _error


    @SuppressLint("CheckResult")
    fun query() {
        gankRepository.getCategoryTypes(CATEGORY_ARTICLE).doOnSubscribe {
            compositeDisposable.add(it)
        }.subscribe({
            _categories.postValue(it)
        }, { throwable: Throwable ->
            _error.postValue(throwable)
        })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}