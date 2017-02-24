package com.daigou.client.controller

import com.daigou.client.model.Pages
import com.daigou.client.model.Result
import com.daigou.common.util.UrlConstant
import tornadofx.Controller
import tornadofx.Rest
import tornadofx.toModel

/**
 * Created by wt on 2017/2/13.
 */
abstract class BaseCtrl<out T> : Controller() {
    protected val api: Rest by inject()
    var pages = Pages(1, 20)

    init {
        api.baseURI = UrlConstant.server_url
    }

    abstract fun getList(page: Int, row: Int, option: String): List<T>

    fun getResult(response: Rest.Response): Result {
        return response.one().toModel<Result>()
    }

    fun result(response: Rest.Response): Boolean {
        if (response.ok()) {
            val result = getResult(response)
            if (result.result) {
                return true
            }
        }
        return false
    }
}