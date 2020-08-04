package com.cheney.gankkotlin.util

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import java.util.HashMap

object LifecycleCallbacks {
    const val TAG = "LifecycleCallbacks"

    private val lifecycleCallbacksMap: HashMap<String, FragmentManager.FragmentLifecycleCallbacks> =
        HashMap()


    init {

    }

    fun register(application: Application) {
        application.registerActivityLifecycleCallbacks(object :
            Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
                Log.d(TAG, "${activity.javaClass.simpleName}------>onActivityPaused")
            }

            override fun onActivityStarted(activity: Activity) {
                Log.d(TAG, "${activity.javaClass.simpleName}------>onActivityStarted")
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.d(TAG, "${activity.javaClass.simpleName}------>onActivityDestroyed")
                if (activity is FragmentActivity) {
                    lifecycleCallbacksMap[activity.javaClass.name]?.let {
                        activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(
                            it
                        )
                    }
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                Log.d(TAG, "${activity.javaClass.simpleName}------>onActivitySaveInstanceState")
            }

            override fun onActivityStopped(activity: Activity) {
                Log.d(TAG, "${activity.javaClass.simpleName}------>onActivityStopped")
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d(TAG, "${activity.javaClass.simpleName}------>onActivityCreated")
                if (activity is FragmentActivity) {
                    val fragmentLifecycleCallbacks = FragmentLifecycleCallbacks()
                    lifecycleCallbacksMap[activity.javaClass.name] = fragmentLifecycleCallbacks
                    activity.supportFragmentManager.registerFragmentLifecycleCallbacks(
                        FragmentLifecycleCallbacks(),
                        true
                    )
                }
            }

            override fun onActivityResumed(activity: Activity) {
                Log.d(TAG, "${activity.javaClass.simpleName}------>onActivityResumed")
            }

        })
    }

    class FragmentLifecycleCallbacks : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentAttached(
            fm: FragmentManager,
            f: Fragment,
            context: Context
        ) {
            super.onFragmentAttached(fm, f, context)
            Log.d(TAG, "${f.javaClass.simpleName}------>onFragmentAttached")
        }

        override fun onFragmentCreated(
            fm: FragmentManager,
            f: Fragment,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentCreated(fm, f, savedInstanceState)
            Log.d(TAG, "${f.javaClass.simpleName}------>onFragmentCreated")
        }

        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            Log.d(TAG, "${f.javaClass.simpleName}------>onFragmentViewCreated")

        }

        override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
            super.onFragmentResumed(fm, f)
            Log.d(TAG, "${f.javaClass.simpleName}------>onFragmentResumed")
        }

        override fun onFragmentPaused(fm: FragmentManager, f: Fragment) {
            super.onFragmentPaused(fm, f)
            Log.d(TAG, "${f.javaClass.simpleName}------>onFragmentPaused")
        }

        override fun onFragmentStopped(fm: FragmentManager, f: Fragment) {
            super.onFragmentStopped(fm, f)
            Log.d(TAG, "${f.javaClass.simpleName}------>onFragmentStopped")
        }

        override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
            super.onFragmentViewDestroyed(fm, f)
            Log.d(TAG, "${f.javaClass.simpleName}------>onFragmentViewDestroyed")
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment) {
            super.onFragmentDestroyed(fm, f)
            Log.d(TAG, "${f.javaClass.simpleName}------>onFragmentDestroyed")
        }

        override fun onFragmentDetached(fm: FragmentManager, f: Fragment) {
            super.onFragmentDetached(fm, f)
            Log.d(TAG, "${f.javaClass.simpleName}------>onFragmentDetached")
        }
    }
}