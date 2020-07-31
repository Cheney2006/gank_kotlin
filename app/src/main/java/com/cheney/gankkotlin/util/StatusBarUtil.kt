package com.cheney.gankkotlin.util

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.WindowManager

object StatusBarUtil {
    fun setStatusBar(activity: Activity) {
        val window = activity.window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.decorView.systemUiVisibility =
                window.decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        } else {
            val layoutParams = window.attributes
            layoutParams.flags =
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or layoutParams.flags
        }
    }
}