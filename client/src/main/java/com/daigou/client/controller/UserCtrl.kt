package com.daigou.client.controller

import com.alibaba.fastjson.JSONArray
import com.daigou.client.model.Pages
import com.daigou.client.model.PagesModel
import com.daigou.client.model.UserModel
import com.daigou.client.util.mapToParams
import com.daigou.common.constant.UrlConstant
import com.daigou.core.domain.User
import tornadofx.*


/**
 * Created by wt on 2017/2/10.
 */
class UserCtrl : BaseCtrl<User>() {

    override fun getList(pagesModel: PagesModel): List<User> {
        val data = hashMapOf<String, Any>()
        data.put("pageNum", pagesModel.pageNum.value ?: 1)
        data.put("pageSize", pagesModel.pageSize.value ?: 20)
        data.put("search", pagesModel.search.value)
        data.put("startTime", pagesModel.startTime.value)
        data.put("endTime", pagesModel.endTime.value)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.user_list + params)
//        val results = doPost(UrlConstant.user_list, data)
//        if(results.result){
//            val pages = JSONArray.parseObject(results.data, com.daigou.core.domain.Pages::class.java)
//            if (pages.data.isNotEmpty()) {
//                return JSONArray.parseArray(pages.data, User::class.java)
//            }
//        }
        val result = result(response)
        if (result.result) {
            pages = result.data.toModel<Pages>()
            if (pages.data != null) {
                return JSONArray.parseArray(pages.data.toString(), User::class.java)
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
        val response = api.post(UrlConstant.user_add + params)
        return result(response).result
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
        val response = api.post(UrlConstant.user_edit + params)
        return result(response).result
    }

    fun deleteUser(uuid: String): Boolean {
        val data = hashMapOf<String, String>()
        data.put("uuid", uuid)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.user_delete + params)
        return result(response).result
    }

    override fun getAll(pagesModel: PagesModel): List<User> {
        val data = hashMapOf<String, Any>()
        data.put("search", pagesModel.search.value)
        data.put("startTime", pagesModel.startTime.value)
        data.put("endTime", pagesModel.endTime.value)
        val params = mapToParams(data)
        val response = api.post(UrlConstant.user_export + params)
        val result = result(response)
        if (result.result) {
//                pages = result.data.toModel<Pages>()
            if (pages.data != null) {
                return JSONArray.parseArray(pages.data.toString(), User::class.java)
            }
        }
        return emptyList()
    }
}