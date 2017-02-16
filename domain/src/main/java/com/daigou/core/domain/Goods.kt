package com.daigou.core.domain

import java.io.Serializable
import java.util.*

/**
 * Created by wt on 2017/2/15.
 */
data class Goods(var uuid: String? = "",
                 var type: String? = "",
                 var name: String? = "",
                 var price: Double = 0.00,
                 var counter: Double = 0.00,
                 var bid: Double = 0.00,
                 var status: Int = 1,
                 var remark: String? = "",
                 var createTime: Date? = null,
                 var updateTime: Date? = null
) : Serializable {
    private val serialVersionUID = 1L
}