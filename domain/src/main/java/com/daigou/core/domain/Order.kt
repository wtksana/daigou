package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/15.
 */
data class Order(var uuid: String? = "",
                 var userUuid: String? = "",
                 var goodsUuid: String? = "",
                 var opUuid: String? = "",
                 var quantity: Int = 0,
                 var status: Int = 1,
                 var remark: String? = "",
                 var doneTime: Date? = null,
                 var createTime: Date? = null,
                 var updateTime: Date? = null
) : Serializable {
    private val serialVersionUID = 1L
}