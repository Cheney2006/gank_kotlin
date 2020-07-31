package com.cheney.gankkotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import java.lang.Exception

class CustomTabLayout : TabLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (tabCount == 0) return
        try {
            val tabLayout = getChildAt(0) as ViewGroup
            var widthOfAllTabs = 0;
            for (i in 0 until tabLayout.childCount){
                widthOfAllTabs += tabLayout.getChildAt(i).measuredWidth
            }
            tabMode=if(widthMeasureSpec<=measuredWidth) MODE_FIXED else MODE_SCROLLABLE
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}