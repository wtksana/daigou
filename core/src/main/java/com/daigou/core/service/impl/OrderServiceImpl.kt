package com.daigou.core.service.impl

import com.alibaba.fastjson.JSONArray
import com.daigou.core.dao.OrderDetailMapper
import com.daigou.core.dao.OrderMapper
import com.daigou.core.domain.Order
import com.daigou.core.domain.OrderDetail
import com.daigou.core.service.OrderService
import com.daigou.core.util.Pages
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.apache.commons.codec.binary.Base64
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
    @Autowired
    private val orderDetailMapper: OrderDetailMapper? = null

    override fun save(model: Order): Boolean {
        if (model.userUuid.isNullOrBlank() || model.account < 0) {
            return false
        }
        if (model.uuid.isNullOrBlank()) {
            model.uuid = UUID.randomUUID().toString().replace("-", "")
        }
        mapper!!.save(model)
        val details = JSONArray.parseArray(kotlin.text.String(Base64.decodeBase64(model.detail.replace("\n", "").replace(" ", "+"))), OrderDetail::class.java)
        details.forEach {
            if (it.uuid.isNullOrBlank()) {
                it.orderUuid = model.uuid
                it.uuid = UUID.randomUUID().toString().replace("-", "")
            }
            orderDetailMapper!!.save(it)
        }
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
        if (list.isNotEmpty()) {
            for (order in list) {
                val detail = orderDetailMapper!!.getListByOrderUuid(order.uuid)
                order.detail = Base64.encodeBase64String(JSONArray.toJSONString(detail).toByteArray())
            }
        }
        val newPages = Pages(list as Page<Order>)
        return newPages
    }
}