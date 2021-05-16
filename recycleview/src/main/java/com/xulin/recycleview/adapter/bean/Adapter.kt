package com.xulin.recycleview.adapter.bean

/**
 * 通用适配器 Bean
 *
 * @author xulin
 */
abstract class Adapter {

    /**
     * 显示条目总数量
     */
    val size get() = types.size

    /**
     * 显示条目类型
     */
    val types = arrayListOf<Int>()

    /**
     * 与显示条目类型对应 Bean
     */
    val items = arrayListOf<Any>()
}