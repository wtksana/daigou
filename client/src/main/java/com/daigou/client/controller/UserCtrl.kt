package com.daigou.client.controller

import com.alibaba.fastjson.JSONArray
import com.daigou.client.model.Pages
import com.daigou.client.model.PagesModel
import com.daigou.client.model.UserModel
import com.daigou.client.util.mapToParams
import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.User
import tornadofx.toModel


/**
 * Created by wt on 2017/2/10.
 */
class UserCtrl : BaseCtrl<User>() {

    override fun getList(pagesModel: PagesModel): List<User> {
        val data = hashMapOf<String, Any>()
        data.put("page", pagesModel.page.value ?: 1)
        data.put("row", pagesModel.row.value ?: 20)
        data.put("option", pagesModel.option.value)
        data.put("startTime", pagesModel.startTime.value)
        data.put("endTime", pagesModel.endTime.value)
        val params = mapToParams(data)
        val response = api.get(UrlConstant.user_list + params)
        if (response.ok()) {
            val result = getResult(response)
            if (result.result) {
                pages = result.data.toModel<Pages>()
                if (pages.data != null) {
                    return JSONArray.parseArray(pages.data.toString(), User::class.java)
                }
            }
        }
        return emptyList()
    }

    fun addUser(model: UserModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("userName", model.userName.value)
        data.put("realName", model.realName.value)
        data.put("mobile", model.mobile.value)
        data.put("address", model.address.value)
        data.put("remark", model.remark.value)
        val params = mapToParams(data)
        val response = api.get(UrlConstant.user_add + params)
        return result(response)
    }

    fun editUser(model: UserModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", model.uuid.value)
        data.put("userName", model.userName.value)
        data.put("realName", model.realName.value)
        data.put("mobile", model.mobile.value)
        data.put("address", model.address.value)
        data.put("remark", model.remark.value)
        val params = mapToParams(data)
        val response = api.get(UrlConstant.user_edit + params)
        return result(response)
    }

    fun deleteUser(uuid: String): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", uuid)
        val params = mapToParams(data)
        val response = api.get(UrlConstant.user_delete + params)
        return result(response)
    }

    fun getAll(pagesModel: PagesModel): List<User> {
        val data = hashMapOf<String, Any>()
        data.put("option", pagesModel.option.value)
        data.put("startTime", pagesModel.startTime.value)
        data.put("endTime", pagesModel.endTime.value)
        val params = mapToParams(data)
        val response = api.get(UrlConstant.user_export + params)
        if (response.ok()) {
            val result = getResult(response)
            if (result.result) {
                pages = result.data.toModel<Pages>()
                if (pages.data != null) {
                    return JSONArray.parseArray(pages.data.toString(), User::class.java)
                }
            }
        }
        return emptyList()
    }
}