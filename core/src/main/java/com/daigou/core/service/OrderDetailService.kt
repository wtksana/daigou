package com.daigou.core.service

import com.daigou.core.domain.OrderDetail

/**
 * Created by wt on 2017/2/17.
 */
interface OrderDetailService {

    fun save(model: OrderDetail): Boolean

    fun update(model: OrderDetail): Boolean

    fun deleteByUuid(uuid: String): Boolean

    fun getByUuid(uuid: String): OrderDetail?

    fun countByModel(model: OrderDetail): Int

    fun getListByOrderUuid(orderUuid: String): List<OrderDetail>

}