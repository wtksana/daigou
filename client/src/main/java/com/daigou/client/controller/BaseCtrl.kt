package com.daigou.client.controller

import com.daigou.client.model.Pages
import com.daigou.client.model.PagesModel
import com.daigou.client.model.Result
import tornadofx.*

/**
 * Created by wt on 2017/2/13.
 */
abstract class BaseCtrl<out T> : Controller() {
    protected val api: Rest by inject()
    var pages = Pages(1, 20)

//    init {
//        api.baseURI = UrlConstant.server_url
//    }

    abstract fun getList(pagesModel: PagesModel): List<T>

    abstract fun getAll(pagesModel: PagesModel): List<Any>

    fun getResult(response: Rest.Response): Result {
        return response.one().toModel<Result>()
    }

    fun result(response: Rest.Response): Result {
        val result: Result
        try {
            if (response.ok()) {
                result = response.one().toModel<Result>()
            } else {
                throw Exception("response returned ${response.statusCode} ${response.reason}")
            }
        } finally {
            response.consume()
        }
        return result
    }
}