package com.daigou.core.service

import com.daigou.core.domain.Operator
import com.daigou.core.domain.Order
import com.daigou.core.domain.OrderDetail
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/15.
 */
interface OrderService {

    fun save(model: Order, operator: Operator): Boolean

    fun update(model: Order, operator: Operator): Boolean

    fun deleteByUuid(uuid: String, operator: Operator): Boolean

    fun orderDone(uuid: String, operator: Operator): Boolean

    fun getByUuid(uuid: String): Order?

    fun countByModel(model: Order): Int

    fun getListByPages(pages: Pages<Order>): Pages<Order>

    fun getAllByPages(pages: Pages<Order>): List<Order>

    fun getOrderDetailListByOrderUuid(uuid: String): List<OrderDetail>
}