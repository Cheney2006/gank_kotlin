package com.cheney.gankkotlin.ui.girl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GirlViewModel : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()

    val isLoading get() = _isLoading


//    private val _text = MutableLiveData<String>().apply {
//        value = "This is notifications Fragment"
//    }
//    val text: LiveData<String> = _text

    fun refresh() {

    }
}