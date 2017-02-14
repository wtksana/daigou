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

    val selectedUser = UserModel()
    override fun getList(page: Int, row: Int, option: String): Boolean {
        val data = hashMapOf<String, String>()
        data.put("page", page.toString())
        data.put("row", row.toString())
        data.put("option", option)
        val params = mapToParams(data)
        val rt = api.get(UrlConstant.user_list + params)
        if (rt.ok()) {
            pages = rt.one().getJsonObject("data").toModel<Pages>()
            if (pages.data != null) {
                list = JSONArray.parseArray(pages.data.toString(), User::class.java).observable()
            } else {
                list = FXCollections.observableArrayList<User>()
            }
            return true
        }
        return false
    }

    fun addUser(user: UserModel): Boolean {
        val data = hashMapOf<String, String>()
        data.put("wechat", user.wechat.value)
        data.put("realName", user.realName.value)
        data.put("mobile", user.mobile.value)
        data.put("address", user.address.value)
        data.put("remark", user.remark.value)
        val params = mapToParams(data)
        val rt = api.get(UrlConstant.user_add + params)
        if (rt.ok()) {
            println("ok")
            return true
        }
        return false
    }
}