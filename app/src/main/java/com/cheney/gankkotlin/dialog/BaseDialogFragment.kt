package com.cheney.gankkotlin.dialog

import android.R.attr
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


open class BaseDialogFragment : DialogFragment(){
    override fun show(manager: FragmentManager, tag: String?) {
        //todo
        //避免重复添加的异常 java.lang.IllegalStateException: Fragment already added
//        val fragment: Fragment? = manager.findFragmentByTag(tag)
//        fragment?.let { manager.beginTransaction().remove(fragment).commitAllowingStateLoss()
//            Log.d("BaseDialogFragment","remove")
//        }

        //避免状态丢失的异常 java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        val mClass = DialogFragment::class.java
        val dismissed = mClass.getDeclaredField("mDismissed")
        dismissed.isAccessible = true
        dismissed.set(this, false)

        val shownByMe = mClass.getDeclaredField("mShownByMe")
        shownByMe.isAccessible = true
        shownByMe.set(this, true)
        manager.beginTransaction().add(this, tag).commitAllowingStateLoss()
        //或者直接捕获 show 方法异常
    }
}