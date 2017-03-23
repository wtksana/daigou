package com.daigou.client.controller

import com.alibaba.fastjson.JSONArray
import com.daigou.client.model.OrderModel
import com.daigou.client.model.Pages
import com.daigou.client.model.PagesModel
import com.daigou.client.util.mapToParams
import com.daigou.common.constant.UrlConstant
import com.daigou.core.domain.Order
import org.apache.commons.codec.binary.Base64
import tornadofx.*

/**
 * Created by wt on 2017/2/23.
 */
class OrderCtrl : BaseCtrl<Order>() {

    override fun getList(pagesModel: PagesModel): List<Order> {
        val data = hashMapOf<String, Any>()
        data.put("pageNum", pagesModel.pageNum.value ?: 1)
        data.put("pageSize", pagesModel.pageSize.value ?: 20)
        data.put("search", pagesModel.search.value)
        data.put("startTime", pagesModel.startTime.value)
        data.put("endTime", pagesModel.endTime.value)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.order_list + params)
        val result = result(response)
        if (result.result) {
            pages = result.data.toModel<Pages>()
            if (pages.data != null) {
                return JSONArray.parseArray(pages.data.toString(), Order::class.java)
            }
        }
        return emptyList()
    }

    override fun getAll(pagesModel: PagesModel): List<Any> {
        val data = hashMapOf<String, Any>()
        data.put("search", pagesModel.search.value)
        data.put("startTime", pagesModel.startTime.value)
        data.put("endTime", pagesModel.endTime.value)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.order_export + params)
        val result = result(response)
        if (result.result) {
            pages = result.data.toModel<Pages>()
            if (pages.data != null) {
                return JSONArray.parseArray(pages.data.toString(), Order::class.java)
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
        return result(response).result
    }

    fun editOrder(model: OrderModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", model.uuid.value)
        data.put("userUuid", model.userUuid.value)
        data.put("account", model.account.value.toString())
        data.put("remark", model.remark.value)
//        data.put("detail", Base64.encodeBase64String(model.detail.value.toByteArray()))
        val params = mapToParams(data)
        val response = api.post(UrlConstant.order_edit + params)
        return result(response).result
    }

    fun deleteOrder(uuid: String): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", uuid)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.order_delete + params)
        return result(response).result
    }
}