package com.daigou.client.controller

import tornadofx.Controller
import tornadofx.Rest

/**
 * Created by wt on 2017/2/9.
 */
class OperatorCtrl : Controller() {

    private val api: Rest by inject()

    init {
        api.baseURI = "http://127.0.0.1:8080"
    }

    fun login(name: String, pwd: String): Boolean {
        val path = "/sys/login.html?uuid=54c294528b5041ff838380676a2ed643"
        val rt = api.post(path)
        if (rt.ok()) {
            println(rt.one().getJsonObject("data"))
        }
        return false
    }

}