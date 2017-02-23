package com.daigou.core.dao

import com.daigou.core.domain.OrderDetail

/**
 * Created by wt on 2017/2/23.
 */
interface OrderDetailMapper : BaseMapper<OrderDetail> {

    fun getListByOrderUuid(orderUuid: String): List<OrderDetail>

}