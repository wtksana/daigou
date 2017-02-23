package com.daigou.client.controller

import com.alibaba.fastjson.JSONArray
import com.daigou.client.model.Pages
import com.daigou.client.util.mapToParams
import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.Order
import tornadofx.toModel

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

}