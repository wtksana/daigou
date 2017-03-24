package com.daigou.core.dao

import com.daigou.core.domain.Order
import com.daigou.core.domain.OrderDetail
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/15.
 */
interface OrderMapper : BaseMapper<Order> {

    fun getListByPages(pages: Pages<Order>): List<Order>

    fun getOrderDetailListByOrderUuid(uuid: String): List<OrderDetail>

}