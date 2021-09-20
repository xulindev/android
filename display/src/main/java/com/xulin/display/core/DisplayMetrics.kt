package com.xulin.display.core

import android.content.Context
import android.os.Build
import android.view.WindowManager

/**
 * 屏幕信息辅助类 获取屏幕的高度和宽度
 *
 * 屏幕宽度默认值 1080px
 *
 * 屏幕高度默认值 1920px
 *
 * 设计图宽度默认值 720px
 *
 * 请使用 getDisplayMetrics(context, designWidth) 进行屏幕信息初始化
 *
 * @author xulin
 */
object DisplayMetrics {

    /**
     * 屏幕宽度
     */
    var width = 1080

    /**
     * 屏幕高度
     */
    var height = 1920

    /**
     * 屏幕宽度 : 设计图宽度
     */
    var density = width / 720f

    /**
     * 方法没有对全面屏适配
     *
     * 无法获取真实的内容高度
     *
     * 需减去屏幕底部状态栏高度
     *
     * 才可以获取真实内容高度
     *
     * 真实内容宽度可以保证准确
     *
     * eg: 可以采取干掉用户虚拟导航栏来保证屏幕高度准确
     *
     * @param context     用于获取屏幕信息
     * @param designWidth 设计图宽度
     */
    fun getDisplayMetrics(context: Context, designWidth: Float) {
        val metrics = getDisplayMetrics(context)
        width = metrics.widthPixels
        height = metrics.heightPixels
        density = width / designWidth
    }

    /**
     * @param context 用于获取屏幕信息
     * @return [android.util.DisplayMetrics]
     */
    private fun getDisplayMetrics(context: Context): android.util.DisplayMetrics {
        val metrics = android.util.DisplayMetrics()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            context.display?.getRealMetrics(metrics)
        } else {
            (context.getSystemService(
                Context.WINDOW_SERVICE
            ) as WindowManager)
                .defaultDisplay
                .getRealMetrics(metrics)
        }
        return metrics
    }
}