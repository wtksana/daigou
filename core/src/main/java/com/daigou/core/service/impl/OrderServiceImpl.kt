package com.daigou.core.service.impl

import com.daigou.core.dao.OrderMapper
import com.daigou.core.domain.Order
import com.daigou.core.service.OrderService
import com.daigou.core.util.Pages
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by wt on 2017/2/15.
 */
@Service
open class OrderServiceImpl : OrderService {
    @Autowired
    private val mapper: OrderMapper? = null

    override fun save(model: Order): Boolean {
        if (model.userUuid.isNullOrBlank() || model.account < 0) {
            return false
        }
        if (model.uuid.isNullOrBlank()) {
            model.uuid = UUID.randomUUID().toString().replace("-", "")
        }
        mapper!!.save(model)
        return true
    }

    override fun update(model: Order): Boolean {
        if (model.uuid.isNullOrBlank() || model.userUuid.isNullOrBlank() || model.account < 0) {
            return false
        }
        mapper!!.update(model)
        return true
    }

    override fun deleteByUuid(uuid: String): Boolean {
        if (uuid.isBlank()) {
            return false
        }
        mapper!!.deleteByUuid(uuid)
        return true
    }

    override fun getByUuid(uuid: String): Order? {
        if (uuid.isBlank()) {
            return null
        }
        return mapper!!.getByUuid(uuid)
    }

    override fun countByModel(model: Order): Int {
        return mapper!!.countByModel(model)
    }

    override fun getListByPages(pages: Pages<Order>): Pages<Order> {
        PageHelper.startPage<Order>(pages.page, pages.row, pages.order)
        val list = mapper!!.getListByPages(pages)
        val pages = Pages(list as Page<Order>)
        return pages
    }
}