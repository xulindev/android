package com.xulin.retrofit.bean

/**
 * 网络请求错误信息
 *
 * @property code Int 网络请求错误码
 * @property message String 网络请求错误信息
 * @author xulin
 */
class Error(
    val code: Int,
    val message: String
)