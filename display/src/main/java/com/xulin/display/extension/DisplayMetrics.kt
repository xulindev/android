package com.xulin.display.extension

import com.xulin.display.core.DisplayMetrics

/**
 * com.xulin.display.core.DisplayMetrics 拓展类
 *
 * @author xulin
 */

/**
 * 获取手机屏幕宽度
 * @return Int 手机屏幕宽度
 */
val windowWidth: Int
    get() {
        return DisplayMetrics.width
    }

/**
 * 获取手机屏幕高度
 * @return Int 手机屏幕高度
 */
val windowHeight: Int
    get() {
        return DisplayMetrics.height
    }