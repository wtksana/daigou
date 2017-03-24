package com.daigou.core.domain

import java.io.Serializable

/**
 * Created by wt on 2017/2/23.
 */
data class OrderDetail(var uuid: String = "",
                       var orderUuid: String = "", //订单uuid
                       var goodsUuid: String = "", //商品uuid
                       var goodsName: String = "",//商品名称
                       var quantity: Int = 0, //商品数量
                       var account: Double = 0.00, //总价
                       var status: Int = 1//状态 1：下单 2：完成 0：删除
) : Serializable {
    private val serialVersionUID = 1L
}