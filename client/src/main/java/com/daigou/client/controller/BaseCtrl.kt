package com.daigou.client.controller

import com.daigou.client.model.Pages
import com.daigou.common.util.UrlConstant
import javafx.collections.FXCollections
import tornadofx.Controller
import tornadofx.Rest

/**
 * Created by wt on 2017/2/13.
 */
abstract class BaseCtrl<T> : Controller() {
    protected val api: Rest by inject()
    var list = FXCollections.observableArrayList<T>()
    var pages = Pages(1, 20)

    init {
        api.baseURI = UrlConstant.server_url
    }

    abstract fun getList(page: Int, row: Int, option: String): Boolean
}