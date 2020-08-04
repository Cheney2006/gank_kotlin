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
import android.view.ViewConfiguration
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.cheney.gankkotlin.R
import kotlin.math.abs


/**
 * ViewPager2滑动事件冲突
 * 1、SwipeRefreshLayout嵌套RecyclerView嵌套ViewPager2
 * <pre>
 *      <NestedScrollableLayout android:orientation="horizontal">
 *           <ViewPager2/>
 *      </NestedScrollableLayout>
 * </pre>
 * 2. ViewPager2嵌套SwipeRefreshLayout和RecyclerView
 * <pre>
 *      <NestedScrollableLayout android:orientation="verticle">
 *          <SwipleRefreshLayout>
 *               <RecyclerView />
 *          </SwipleRefreshLayout>
 *      </NestedScrollableLayout>
 * </pre>
 */
class NestedScrollableLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val HORIZONTAL = LinearLayout.HORIZONTAL
        private const val VERTICAL = LinearLayout.VERTICAL
    }

    private var downX: Float = 0f
    private var downY: Float = 0f
    private var isDragged = false
    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop

    private var orientation = HORIZONTAL

    init {
        attrs?.let {
            val a = context.obtainStyledAttributes(attrs, R.styleable.NestedScrollableLayout)
            orientation =
                a.getInt(R.styleable.NestedScrollableLayout_android_orientation, HORIZONTAL)
            a.recycle()
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = ev.x
                downY = ev.y
                isDragged = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isDragged) {
                    val dx = abs(ev.x - downX)
                    val dy = abs(ev.y - downY)
                    if (orientation == HORIZONTAL) {
                        isDragged = dx > touchSlop && dx > dy
                    } else if (orientation == VERTICAL) {
                        isDragged = dy > touchSlop && dy > dx
                    }
                }
                parent.requestDisallowInterceptTouchEvent(isDragged)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isDragged = false
                parent.requestDisallowInterceptTouchEvent(false)
            }
        }
        return super.onInterceptTouchEvent(ev)
    }
}