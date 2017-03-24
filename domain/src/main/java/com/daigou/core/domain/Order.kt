package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/15.
 */
data class Order(var uuid: String = "",
                 var userUuid: String = "",//用户id
                 var userName: String = "",//用户名
                 var account: Double = 0.00,//总价
                 var detail: String = "",//详情
                 var opUuid: String = "",//操作员uuid
                 var status: Int = 1,//状态 1：下单 2：完成 0：删除
                 var remark: String = "",//备注
                 var doneTime: Date? = null,//订单完成时间
                 var createTime: Date? = null,
                 var updateTime: Date? = null
) : Serializable {
    private val serialVersionUID = 1L
}