package com.daigou.core.domain

import java.io.Serializable

/**
 * Created by wt on 2017/2/23.
 */
data class OrderDetail(var uuid: String = "",
                       var orderUuid: String = "",
                       var goodsUuid: String = "",
                       var goodsName: String = "",
                       var quantity: Int = 0,
                       var account: Double = 0.00,
                       var status: Int = 1
) : Serializable {
    private val serialVersionUID = 1L
}