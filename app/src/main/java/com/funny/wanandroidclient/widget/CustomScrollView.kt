package com.funny.wanandroidclient.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.widget.ScrollView



/**
 * @author pengl
 */
open class CustomScrollView constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ScrollView(context,attrs,defStyleAttr) {
    private var downX: Int = 0
    private var downY: Int = 0
    private var touchSlop: Int = 0

    //渐变开始位置
    private var transStartY = 100
    //渐变结束位置
    private var transEndY = 350

    constructor(context: Context, attrs: AttributeSet? = null) : this(context,attrs,0)

    constructor(context: Context) : this(context,null,0)

    private var translucentChangedListener: CustomScrollView.TranslucentChangedListener? = null

    interface TranslucentChangedListener {
        /**
         * 透明度变化，取值范围0-255
         *
         * @param transAlpha
         */
        fun onTranslucentChanged(transAlpha: Int)
    }

    init {
        this.touchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }


    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        val action = e.action
        when (action) {
            0 -> {
                this.downX = e.rawX.toInt()
                this.downY = e.rawY.toInt()
            }
            2 -> {
                val moveY = e.rawY.toInt()
                if (Math.abs(moveY - this.downY) > this.touchSlop) {
                    return true
                }
            }
        }

        return super.onInterceptTouchEvent(e)
    }

    fun setTranslucentChangedListener(translucentChangedListener: TranslucentChangedListener) {
        this.translucentChangedListener = translucentChangedListener
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        if (translucentChangedListener != null) {
            val alpha = getTransAlpha()
            translucentChangedListener!!.onTranslucentChanged(alpha)
        }

    }

    /**
     * 获取透明度
     *
     * @return
     */
    private fun getTransAlpha(): Int {
        val scrollY = scrollY.toFloat()
        return if (transStartY != 0) {
            if (scrollY <= transStartY) {
                0
            } else if (scrollY >= transEndY) {
                255
            } else {
                ((scrollY - transStartY) / (transEndY - transStartY) * 255).toInt()
            }
        } else {
            if (scrollY >= transEndY) {
                255
            } else ((transEndY - scrollY) / transEndY * 255).toInt()
        }
    }
}