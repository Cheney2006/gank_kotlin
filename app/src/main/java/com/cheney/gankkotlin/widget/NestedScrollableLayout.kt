package com.cheney.gankkotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.cheney.gankkotlin.R
import kotlin.math.abs


/**
 * ViewPager2滑动事件冲突
 * 1、SwipeRefreshLayout嵌套RecyclerView嵌套ViewPager2
 * <pre>
 *      <FixDragLayout android:orientation="horizontal">
 *           <ViewPager2/>
 *      </FixDragLayout>
 * </pre>
 * 2. ViewPager2嵌套SwipeRefreshLayout和RecyclerView
 * <pre>
 *      <FixDragLayout android:orientation="verticle">
 *          <SwipleRefreshLayout>
 *               <RecyclerView />
 *          </SwipleRefreshLayout>
 *      </FixDragLayout>
 * </pre>
 */
class NestedScrollableLayout : FrameLayout {

    companion object {
        const val HORIZONTAL: Int = LinearLayout.HORIZONTAL;
        const val VERTICAL: Int = LinearLayout.VERTICAL;
    }

    private var orientation: Int = VERTICAL

    private var touchSlop: Int = 0
    private var downX: Float = 0f
    private var downY: Float = 0f
    private var isDragged: Boolean = false


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.NestedScrollableLayout, defStyle, 0
        )

        orientation = a.getInt(
            R.styleable.NestedScrollableLayout_android_orientation, VERTICAL
        )

        a.recycle()


    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x;
                downY = ev.y
                isDragged = false;
            }

            MotionEvent.ACTION_MOVE ->{
                if (!isDragged) {
                    val dx = abs(ev.x) - downX
                    val dy = abs(ev.y) - downY
                    if (orientation == HORIZONTAL) {
                        isDragged = dx > touchSlop && dx > dy
                    } else if (orientation == VERTICAL) {
                        isDragged = dy > touchSlop && dy > dx
                    }
                }
                parent.requestDisallowInterceptTouchEvent(isDragged)
            }

            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL -> {
                isDragged=false
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.onInterceptTouchEvent(ev)
    }


}
