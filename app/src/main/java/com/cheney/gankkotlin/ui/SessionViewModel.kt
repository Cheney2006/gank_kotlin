package com.cheney.gankkotlin.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cheney.gankkotlin.bean.ProgressBean
import javax.inject.Inject

class SessionViewModel @Inject constructor() : ViewModel() {
    val progressLiveData = MutableLiveData<ProgressBean>()


}