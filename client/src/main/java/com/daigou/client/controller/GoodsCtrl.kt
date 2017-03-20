package com.daigou.client.controller

import com.alibaba.fastjson.JSONArray
import com.daigou.client.model.GoodsModel
import com.daigou.client.model.Pages
import com.daigou.client.model.PagesModel
import com.daigou.client.util.mapToParams
import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.Goods
import com.daigou.core.domain.GoodsType
import tornadofx.toModel


/**
 * Created by wt on 2017/2/10.
 */
class GoodsCtrl : BaseCtrl<Goods>() {

    override fun getList(pagesModel: PagesModel): List<Goods> {
        val data = hashMapOf<String, Any>()
        data.put("page", pagesModel.page.value ?: 1)
        data.put("row", pagesModel.row.value ?: 20)
        data.put("option", pagesModel.option.value)
        data.put("startTime", pagesModel.startTime.value)
        data.put("endTime", pagesModel.endTime.value)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.goods_list + params)
        if (response.ok()) {
            val result = getResult(response)
            if (result.result) {
                pages = result.data.toModel<Pages>()
                if (pages.data != null) {
                    return JSONArray.parseArray(pages.data.toString(), Goods::class.java)
                }
            }
        }
        return emptyList()
    }

    override fun getAll(pagesModel: PagesModel): List<Any> {
        val data = hashMapOf<String, Any>()
        data.put("option", pagesModel.option.value)
        data.put("startTime", pagesModel.startTime.value)
        data.put("endTime", pagesModel.endTime.value)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.goods_export + params)
        if (response.ok()) {
            val result = getResult(response)
            if (result.result) {
                pages = result.data.toModel<Pages>()
                if (pages.data != null) {
                    return JSONArray.parseArray(pages.data.toString(), Goods::class.java)
                }
            }
        }
        return emptyList()
    }


    fun addGoods(model: GoodsModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("typeUuid", model.typeUuid.value)
        data.put("name", model.name.value)
        data.put("price", model.price.value.toString())
        data.put("bid", model.bid.value.toString())
        data.put("counter", model.counter.value.toString())
        data.put("remark", model.remark.value)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.goods_add + params)
        return result(response)
    }

    fun editGoods(model: GoodsModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", model.uuid.value)
        data.put("typeUuid", model.typeUuid.value)
        data.put("name", model.name.value)
        data.put("price", model.price.value.toString())
        data.put("bid", model.bid.value.toString())
        data.put("counter", model.counter.value.toString())
        data.put("remark", model.remark.value)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.goods_edit + params)
        return result(response)
    }

    fun deleteGoods(uuid: String): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", uuid)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.goods_delete + params)
        return result(response)
    }

    fun getGoodsTypeList(): List<GoodsType> {
        val response = api.post(UrlConstant.goods_type_list)
        if (response.ok()) {
            val result = response.one()
            if (result.getBoolean("result")) {
                val types = JSONArray.parseArray(result.getJsonArray("data").toString(), GoodsType::class.java)
                return types
            }
        }
        return emptyList()
    }

    fun editGoodsType(uuid: String, type: String): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", uuid)
        data.put("type", type)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.goods_type_edit + params)
        return result(response)
    }

    fun addGoodsType(type: GoodsType): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", type.uuid)
        data.put("type", type.type ?: "")
        val params = mapToParams(data)
        val response = api.post(UrlConstant.goods_type_add + params)
        return result(response)
    }
}