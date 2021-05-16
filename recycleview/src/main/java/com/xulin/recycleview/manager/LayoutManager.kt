package com.xulin.recycleview.manager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * 重写滑动功能
 *
 * @author xulin
 */
class LayoutManager(context: Context?) : LinearLayoutManager(context) {

    /**
     * 是否能垂直滑动
     */
    var scrollVertically = true

    /**
     * 是否能水平滑动
     */
    var scrollHorizontally = true

    override fun canScrollVertically(): Boolean {
        return if (scrollVertically) {
            super.canScrollVertically()
        } else {
            false
        }
    }

    override fun canScrollHorizontally(): Boolean {
        return if (scrollHorizontally) {
            super.canScrollHorizontally()
        } else {
            false
        }
    }
}