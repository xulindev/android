package com.xulin.extension

import android.content.Intent
import android.os.Build

/**
 *
 *
 * @author xulindev@outlook.com
 */

fun Intent.addWritePermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        addFlags(
            Intent.FLAG_GRANT_READ_URI_PERMISSION or
                    Intent.FLAG_GRANT_WRITE_URI_PERMISSION
        )
    }
}

fun Intent.addNewTask() {
    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
}