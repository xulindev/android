package com.xulin.extension

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * android.view.View 拓展类
 *
 * @author xulin
 */

/**
 * 设置View布局参数
 * @param width Int view 需要的宽度
 * @param height Int view 需要的高度
 * @return View android.view.View
 */
fun View.setLayoutParams(width: Int, height: Int): View {
    layoutParams.width = width
    layoutParams.height = height
    return this
}

/**
 * 隐藏软键盘
 */
fun View.hideSoftKeyboard() {
    val inputMethodManager = context.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}