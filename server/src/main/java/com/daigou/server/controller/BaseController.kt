package com.daigou.server.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.ModelAttribute
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/**
 * Created by wt on 2017/2/9.
 */
abstract class BaseController {

    protected val Log = LoggerFactory.getLogger(this.javaClass)!!

    protected lateinit var request: HttpServletRequest
    protected lateinit var response: HttpServletResponse
    protected lateinit var session: HttpSession

    protected val RESULT = "result"
    protected val MSG = "msg"
    protected val DATA = "data"
    protected val URL = "url"

    @ModelAttribute
    fun setReqAndRes(request: HttpServletRequest, response: HttpServletResponse) {
        this.request = request
        this.response = response
        this.session = request.session
    }

    protected fun success(): Map<String, Any> {
        val rst = hashMapOf<String, Any>()
        rst.put(RESULT, true)
        rst.put(MSG, "操作成功")
        return rst
    }

    protected fun fail(): Map<String, Any> {
        val rst = hashMapOf<String, Any>()
        rst.put(RESULT, false)
        rst.put(MSG, "操作失败")
        return rst
    }

    protected fun result(result: Boolean, msg: String): Map<String, Any> {
        val rst = hashMapOf<String, Any>()
        rst.put(RESULT, result)
        rst.put(MSG, msg)
        return rst
    }

    protected fun success(data: Any): Map<String, Any> {
        val rst = hashMapOf<String, Any>()
        rst.put(RESULT, true)
        rst.put(DATA, data)
        rst.put(MSG, "操作成功")
        return rst
    }

    protected fun fail(msg: String): Map<String, Any> {
        val rst = hashMapOf<String, Any>()
        rst.put(RESULT, false)
        rst.put(MSG, msg)
        return rst
    }

}