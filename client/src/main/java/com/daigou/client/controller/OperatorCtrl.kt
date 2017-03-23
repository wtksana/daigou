package com.daigou.client.controller

import com.daigou.client.model.PagesModel
import com.daigou.core.domain.Operator

/**
 * Created by wt on 2017/2/9.
 */
class OperatorCtrl : BaseCtrl<Operator>() {

    override fun getList(pagesModel: PagesModel): List<Operator> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(pagesModel: PagesModel): List<Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun login(userName: String, pwd: String): Boolean {
        val path = "/sys/login.html?userName=$userName&pwd=$pwd"
        val response = api.post(path)
        return result(response).result
    }

}