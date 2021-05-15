package com.xulin.display.extension

import com.xulin.display.core.DisplayMetrics

/**
 * kotlin.Float 拓展类
 *
 * @author xulin
 */

/**
 * 获取与屏幕密度的比例值
 * @return Float 比例计算之后的值
 */
val Float.dp: Float
    get() {
        return this * DisplayMetrics.density
    }