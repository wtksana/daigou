package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/17.
 */
data class GoodsType(var uuid: String = "",//主键
                     var typeName: String? = "",//类型
                     var status: Int = 1,
                     var createTime: Date? = null,
                     var updateTime: Date? = null
) : Serializable {
    private val serialVersionUID = 1L
}