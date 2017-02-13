package com.daigou.client.controller

import com.daigou.client.model.Pages
import javafx.collections.FXCollections
import tornadofx.Controller
import tornadofx.Rest

/**
 * Created by wt on 2017/2/13.
 */
abstract class BaseCtrl<T> : Controller() {
    protected val api: Rest by inject()
    var list = FXCollections.observableArrayList<T>()
    var pages = Pages()

    init {
        api.baseURI = "http://127.0.0.1:8080"
    }

    abstract fun getList(page: Int, row: Int, option: String): Boolean
}