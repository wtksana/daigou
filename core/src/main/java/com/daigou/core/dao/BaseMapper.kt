package com.daigou.core.dao

/**
 * Created by wt on 2017/2/15.
 */
interface BaseMapper<T> {

    fun save(model: T)

    fun update(model: T)

    fun deleteByUuid(uuid: String)

    fun getByUuid(uuid: String): T?

    fun countByModel(model: T): Int
}