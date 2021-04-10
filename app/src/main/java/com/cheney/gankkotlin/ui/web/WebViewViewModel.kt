package com.cheney.gankkotlin.ui.web

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class WebViewViewModel @Inject constructor():ViewModel() {

    val progress= MutableLiveData<Int>()
}