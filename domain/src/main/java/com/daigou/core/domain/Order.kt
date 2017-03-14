package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/15.
 */
data class Order(var uuid: String = "",
                 var userUuid: String = "",
                 var userName: String = "",
                 var account: Double = 0.00,
                 var detail: String = "",
                 var opUuid: String = "",
                 var status: Int = 1,
                 var remark: String = "",
                 var doneTime: Date? = null,
                 var createTime: Date? = null,
                 var updateTime: Date? = null
) : Serializable {
    private val serialVersionUID = 1L
}