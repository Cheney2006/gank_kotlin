package com.cheney.gankkotlin.base.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
            ?: creators.entries.firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
            ?: throw IllegalArgumentException("unknown model class $modelClass")
        @Suppress("UNCHECKED_CAST")
        return creator.get() as T
    }

}

//@Singleton
//class ViewModelFactory @Inject
//constructor(private val creators: Map<Class<out ViewModel>, Provider<ViewModel>>) :
//    ViewModelProvider.Factory {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        var creator: Provider<out ViewModel>? = creators[modelClass]
//        if (creator == null) {
//            for ((key, value) in creators) {
//                if (modelClass.isAssignableFrom(key)) {
//                    creator = value
//                    break
//                }
//            }
//        }
//        if (creator == null) {
//            throw IllegalArgumentException("unknown ViewModel class $modelClass")
//        }
//        try {
//            return creator.get() as T
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//}
