package com.daigou.core.service

import com.daigou.core.domain.Order
import com.daigou.core.util.Pages

/**
 * Created by wt on 2017/2/15.
 */
interface OrderService {

    fun save(model: Order): Boolean

    fun update(model: Order): Boolean

    fun deleteByUuid(uuid: String): Boolean

    fun getByUuid(uuid: String): Order?

    fun countByModel(model: Order): Int

    fun getListByPages(pages: Pages<Order>): Pages<Order>

    fun getAllByPages(pages: Pages<Order>): List<Order>
}