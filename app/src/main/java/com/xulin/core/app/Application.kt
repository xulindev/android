package com.xulin.core.app

import android.app.Application
import com.xulin.display.core.DisplayMetrics

/**
 *
 *
 * @author xulin
 */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        DisplayMetrics.getDisplayMetrics(this, 720f)
    }
}