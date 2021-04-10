package com.cheney.gankkotlin.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {
    var username:MutableLiveData<String> = MutableLiveData();
    var password = MutableLiveData<String>()

    fun login(){

    }
}