package com.daigou.client.controller

import com.alibaba.fastjson.JSONArray
import com.daigou.client.model.Pages
import com.daigou.client.model.UserModel
import com.daigou.client.util.mapToParams
import com.daigou.common.util.UrlConstant
import com.daigou.core.domain.User
import javafx.collections.FXCollections
import tornadofx.observable
import tornadofx.toModel


/**
 * Created by wt on 2017/2/10.
 */
class UserCtrl : BaseCtrl<User>() {

    override fun getList(page: Int, row: Int, option: String): Boolean {
        val data = hashMapOf<String, String>()
        data.put("page", page.toString())
        data.put("row", row.toString())
        data.put("option", option)
        val params = mapToParams(data)
        val response = api.get(UrlConstant.user_list + params)
        if (response.ok()) {
            val result = getResult(response)
            if(!result.result){
                return false
            }
            pages = result.data.toModel<Pages>()
            if (pages.data != null) {
                list = JSONArray.parseArray(pages.data.toString(), User::class.java).observable()
            } else {
                list = FXCollections.observableArrayList<User>()
            }
            return true
        }
        return false
    }

    fun addUser(model: UserModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("wechat", model.wechat.value)
        data.put("realName", model.realName.value)
        data.put("mobile", model.mobile.value)
        data.put("address", model.address.value)
        data.put("remark", model.remark.value)
        val params = mapToParams(data)
        val response = api.get(UrlConstant.user_add + params)
        if (response.ok()) {
            val result = getResult(response)
            if (result.result) {
                return true
            }
        }
        return false
    }

    fun editUser(model: UserModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", model.uuid.value)
        data.put("wechat", model.wechat.value)
        data.put("realName", model.realName.value)
        data.put("mobile", model.mobile.value)
        data.put("address", model.address.value)
        data.put("remark", model.remark.value)
        val params = mapToParams(data)
        val response = api.get(UrlConstant.user_edit + params)
        if (response.ok()) {
            val result = getResult(response)
            if (result.result) {
                return true
            }
        }
        return false
    }
}