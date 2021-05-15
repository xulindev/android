package com.xulin.extension

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.GET_CONFIGURATIONS
import android.net.Uri
import android.os.Build

/**
 * android.content.Context 拓展类
 *
 * @author xulin
 */

/**
 * 获取 App 版本名称
 * @return String 版本名称
 */
val Context.appVersionName: String
    get() {
        return packageManager.getPackageInfo(packageName, GET_CONFIGURATIONS).versionName
    }

/**
 * 获取 App 版本号
 * @return String 版本号
 */
val Context.appVersionCode: Long
    get() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return packageManager.getPackageInfo(packageName, GET_CONFIGURATIONS).longVersionCode
        } else {
            return packageManager.getPackageInfo(
                packageName,
                GET_CONFIGURATIONS
            ).versionCode.toLong()
        }
    }

/**
 * 跳转到拨打电话界面
 * @param phone String
 */
fun Context.dialPhone(phone: String) {
    call(phone, Intent(Intent.ACTION_DIAL))
}

/**
 * 直接拨打电话 必须有 'CALL_PHONE' 权限
 * @param phone String
 */
fun Context.callPhone(phone: String) {
    call(phone, Intent(Intent.ACTION_CALL))
}

/**
 * 拨打电话
 * @param phone String
 * @param intent Intent
 */
fun Context.call(phone: String, intent: Intent) {
    intent.data = Uri.parse("tel:$phone")
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}

/**
 * 跳转到发送短信界面
 * @param phone String
 * @param content String
 */
fun Context.sendSms(phone: String, content: String) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("smsto:$phone")
    intent.putExtra("sms_body", content)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}