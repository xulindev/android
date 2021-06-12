package com.xulin.retrofit.extension

import android.accounts.NetworkErrorException
import com.xulin.retrofit.bean.Error
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * 网络请求 拓展类
 *
 * @author xulin
 */

/**
 * 请求网络数据并处理异常信息
 * @param api [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, Response<T>> 数据请求体
 * @param success [@kotlin.ExtensionFunctionType] SuspendFunction2<CoroutineScope, Response<T>, Unit> 请求成功
 * @param error [@kotlin.ExtensionFunctionType] SuspendFunction2<CoroutineScope, Error, Unit> 请求失败 默认弹出提示
 * @param errorParser [@kotlin.ExtensionFunctionType] SuspendFunction2<CoroutineScope, String, Unit> 请求失败 Json
 * @param complete [@kotlin.ExtensionFunctionType] SuspendFunction1<CoroutineScope, Unit> 请求结束
 */
suspend fun <T> get(
    api: suspend CoroutineScope.() -> T,
    success: suspend CoroutineScope.(T) -> Unit,
    error: suspend CoroutineScope.(Error) -> Unit = {},
    errorParser: suspend CoroutineScope.(String) -> Unit,
    complete: suspend CoroutineScope.() -> Unit = {}
) {
    coroutineScope {
        withContext(Dispatchers.IO) {
            try {
                val res = api()
                withContext(Dispatchers.Main) {
                    success(res)
                }
            } catch (e: Throwable) {
                when (e) {
                    is HttpException -> {
                        try {
                            val body = e.response()!!.errorBody()!!.string()
                            withContext(Dispatchers.Main) {
                                errorParser(body)
                            }
                        } catch (e: Throwable) {
                            withContext(Dispatchers.Main) {
                                error(Error(0, "数据解析失败"))
                            }
                        }
                    }
                    is SocketTimeoutException -> {
                        withContext(Dispatchers.Main) {
                            error(Error(0, "连接超时"))
                        }
                    }
                    is ConnectException -> {
                        withContext(Dispatchers.Main) {
                            error(Error(0, "请检测网络是否成功连接"))
                        }
                    }
                    is UnknownHostException -> {
                        withContext(Dispatchers.Main) {
                            error(Error(0, "请检测网络是否开启"))
                        }
                    }
                    is NetworkErrorException -> {
                        withContext(Dispatchers.Main) {
                            error(Error(0, "网络错误"))
                        }
                    }
                    is InterruptedIOException -> {
                        withContext(Dispatchers.Main) {
                            error(Error(0, "网络连接超时，请检查网络连接"))
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            error(Error(0, "获取数据失败"))
                        }
                    }
                }
            } finally {
                withContext(Dispatchers.Main) {
                    complete()
                }
            }
        }
    }
}