package com.daigou.core.service.impl

import com.daigou.core.dao.OrderDetailMapper
import com.daigou.core.domain.OrderDetail
import com.daigou.core.service.OrderDetailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

/**
 * Created by wt on 2017/2/17.
 */
@Service
open class OrderDetailServiceImpl : OrderDetailService {
    @Autowired
    private val mapper: OrderDetailMapper? = null

    override fun save(model: OrderDetail): Boolean {
        if (model.orderUuid.isNullOrBlank()) {
            return false
        }
        if (model.uuid.isNullOrBlank()) {
            model.uuid = UUID.randomUUID().toString().replace("-", "")
        }
        mapper!!.save(model)
        return true
    }

    override fun update(model: OrderDetail): Boolean {
        if (model.uuid.isNullOrBlank() || model.orderUuid.isNullOrBlank()) {
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

    override fun getByUuid(uuid: String): OrderDetail? {
        if (uuid.isBlank()) {
            return null
        }
        return mapper!!.getByUuid(uuid)
    }

    override fun countByModel(model: OrderDetail): Int {
        return mapper!!.countByModel(model)
    }

    override fun getListByOrderUuid(orderUuid: String): List<OrderDetail> {
        return mapper!!.getListByOrderUuid(orderUuid)
    }

}