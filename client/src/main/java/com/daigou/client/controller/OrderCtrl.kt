package com.daigou.client.controller

import com.alibaba.fastjson.JSONArray
import com.daigou.client.model.OrderModel
import com.daigou.client.model.Pages
import com.daigou.client.util.mapToParams
import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.Order
import org.apache.commons.codec.binary.Base64
import tornadofx.*
import java.nio.charset.StandardCharsets

/**
 * Created by wt on 2017/2/23.
 */
class OrderCtrl : BaseCtrl<Order>() {

    override fun getList(page: Int, row: Int, option: String): List<Order> {
        val data = hashMapOf<String, String>()
        data.put("page", page.toString())
        data.put("row", row.toString())
        data.put("option", option)
        val params = mapToParams(data)
        val response = api.get(UrlConstant.order_list + params)
        if (response.ok()) {
            val result = getResult(response)
            if (result.result) {
                pages = result.data.toModel<Pages>()
                if (pages.data != null) {
                    return JSONArray.parseArray(pages.data.toString(), Order::class.java)
                }
            }
        }
        return emptyList()
    }

    fun addOrder(model: OrderModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("userUuid", model.userUuid.value)
        data.put("account", model.account.value.toString())
        data.put("remark", model.remark.value)
        data.put("detail", Base64.encodeBase64String(model.detail.value.toByteArray()))
        val params = mapToParams(data)
        val response = api.post(UrlConstant.order_add + params)
        return result(response)
    }

    fun editOrder(model: OrderModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", model.uuid.value)
        data.put("userUuid", model.userUuid.value)
        data.put("account", model.account.value.toString())
        data.put("remark", model.remark.value)
//        data.put("detail", Base64.encodeBase64String(model.detail.value.toByteArray()))
        val params = mapToParams(data)
        val response = api.get(UrlConstant.order_edit + params)
        return result(response)
    }
}