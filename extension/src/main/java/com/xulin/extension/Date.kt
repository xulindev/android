package com.xulin.extension

import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期与时间 拓展类
 *
 * @author xulin
 */

/**
 * 获取当前日期
 * @return String 格式 yyyy-MM-dd
 */
fun getDate(): String {
    return getDate("-")
}

/**
 * 获取当前日期
 * @param delimiter String 分隔符
 * @return String 自定义分隔符的日期格式
 */
fun getDate(delimiter: String): String {
    return getDateFormat(delimiter).format(Date())
}

/**
 * 获取当前日期+时间
 * @param delimiter String 分隔符
 * @return String 自定义分隔符的日期格式
 */
fun getDateTime(): String {
    return getDateTime("-")
}

/**
 * 获取当前日期+时间
 * @param delimiter String 分隔符
 * @return String 自定义分隔符的日期格式
 */
fun getDateTime(delimiter: String): String {
    return getDateTimeFormat(delimiter).format(Date())
}

/**
 * 获取当前时间
 * @param delimiter String 分隔符
 * @return String 自定义分隔符的日期格式
 */
fun getTime(): String {
    return getTimeFormat().format(Date())
}

/**
 * 获取指定日期是周几
 *
 * @param date 日期格式为:2018-09-21
 * @return 周一至周日
 */
fun getWeekDes(date: String): String {
    if (date.isEmpty()) {
        return ""
    }
    val weeks =
        arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
    return weeks[getWeek(date) - 1]
}

/**
 * 获取指定日期是星期几
 *
 * @param date 日期格式为:2018-09-21
 * @return 星期一至星期天
 */
fun getWeekDes2(date: String): String {
    if (date.isEmpty()) {
        return ""
    }
    val weeks =
        arrayOf("星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
    return weeks[getWeek(date) - 1]
}

/**
 * 获取指定日期是星期几
 * @param date String 日期格式 yyyy-MM-dd
 * @return Int 1-7 代表周一至周日
 */
fun getWeek(date: String): Int {
    return getWeek(date, "-")
}

/**
 * 获取指定日期是周几
 * @param date String 日期
 * @param delimiter String 日期使用的分隔符
 * @return Int 1-7 代表周一至周日
 */
fun getWeek(date: String, delimiter: String): Int {
    val calendar = Calendar.getInstance()
    try {
        calendar.time = getDateFormat(delimiter).parse(date)!!
    } catch (e: Exception) {
        calendar.time = Date()
    }
    return calendar.get(Calendar.DAY_OF_WEEK)
}

/**
 * 获取指定日期是周几
 * @param date String 日期
 * @param calendar Calendar [Calendar]
 * @param format SimpleDateFormat [SimpleDateFormat]
 * @return Int 1-7 代表周一至周日
 */
fun getWeek(date: String, calendar: Calendar, format: SimpleDateFormat): Int {
    try {
        calendar.time = format.parse(date)!!
    } catch (e: Exception) {
        calendar.time = Date()
    }
    return calendar.get(Calendar.DAY_OF_WEEK)
}

/**
 * 获取日期格式化对象
 * @param delimiter String 分隔符
 * @return SimpleDateFormat 自定义分隔符的日期格式
 */
fun getDateFormat(delimiter: String): SimpleDateFormat =
    SimpleDateFormat("yyyy${delimiter}MM${delimiter}dd", Locale.CHINA)

/**
 * 获取时间格式化对象
 * @return SimpleDateFormat 格式 HH:mm
 */
fun getTimeFormat(): SimpleDateFormat =
    SimpleDateFormat("HH:mm", Locale.CHINA)

/**
 * 获取日期+时间格式化对象
 * @param delimiter String 分隔符
 * @return SimpleDateFormat 自定义分隔符的日期格式
 */
fun getDateTimeFormat(delimiter: String): SimpleDateFormat =
    SimpleDateFormat("yyyy${delimiter}MM${delimiter}dd HH:mm", Locale.CHINA)