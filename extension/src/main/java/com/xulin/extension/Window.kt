package com.xulin.extension

import android.graphics.Color
import android.graphics.Rect
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.xulin.display.extension.windowHeight

/**
 * android.view.Window 拓展类
 *
 * @author xulin
 */

/**
 * 透明状态栏
 */
fun Window.transparentStatusBar() {
    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    statusBarColor = 0x00000000
    navigationBarColor = 0x50333333
}

/**
 * 透明状态栏和导航栏
 * @receiver Window
 */
fun Window.transparentStatusBarSoftKeyboard() {
    clearFlags(
        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
    )
    decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    statusBarColor = Color.TRANSPARENT
}

/**
 * 判断输入框是否显示
 * @receiver Window
 * @return Boolean true显示状态 false隐藏状态
 */
fun Window.softKeyboardShow(): Boolean {
    val rect = Rect()
    decorView.getWindowVisibleDisplayFrame(rect)
    return windowHeight - rect.bottom > windowHeight / 3
}
