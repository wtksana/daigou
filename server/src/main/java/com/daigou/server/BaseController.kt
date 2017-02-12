package com.daigou.server

import com.daigou.core.domain.Pages

/**
 * Created by wt on 2017/2/9.
 */
abstract class BaseController {
    protected val RESULT = "result"
    protected val MSG = "msg"
    protected val DATA = "data"
    protected val URL = "url"
    protected val PAGES = "pages"


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