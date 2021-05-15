package com.xulin.display.extension

import com.xulin.display.core.DisplayMetrics

/**
 * kotlin.Int 拓展类
 *
 * @author xulin
 */

/**
 * 获取与屏幕密度的比例值
 * @return Int 比例计算之后的值
 */
val Int.dp: Int
    get() {
        return (this * DisplayMetrics.density).toInt()
    }