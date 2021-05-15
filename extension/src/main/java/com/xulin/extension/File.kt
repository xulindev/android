package com.xulin.extension

import java.io.File
import java.io.FileInputStream
import java.io.IOException

/**
 * java.io.File 拓展类
 *
 * @author xulin
 */

/**
 * 获取文件大小，单位 GB
 * @return 文件大小，单位 GB
 */
val File.sizeUnitGB: Long
    get() {
        return sizeUnitByte / 1073741824
    }

/**
 * 获取文件大小，单位 MB
 * @return 文件大小，单位 MB
 */
val File.sizeUnitMB: Long
    get() {
        return sizeUnitByte / 1048576
    }

/**
 * 获取文件大小，单位 KB
 * @return 文件大小，单位 KB
 */
val File.sizeUnitKB: Long
    get() {
        return sizeUnitByte / 1024
    }

/**
 * 获取文件大小，单位 Byte
 * @return 文件大小，单位 Byte
 */
val File.sizeUnitByte: Long
    get() {
        var size: Long = 0
        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(this)
            size = fis.available().toLong()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fis?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return size
    }