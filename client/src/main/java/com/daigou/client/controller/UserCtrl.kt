package com.daigou.client.controller

import com.daigou.client.model.Pages
import com.daigou.client.model.User
import com.daigou.client.model.UserModel
import javafx.collections.FXCollections
import tornadofx.Controller
import tornadofx.Rest
import tornadofx.toModel

/**
 * Created by wt on 2017/2/10.
 */
class UserCtrl : Controller() {
    private val api: Rest by inject()

    val selectedUser = UserModel()
    var userList = FXCollections.observableArrayList<User>()

    init {
        api.baseURI = "http://127.0.0.1:8080"
    }

    fun getUserList(page: Int, row: Int): Boolean {
        val path = "/user/userList.html?page=$page&row=$row"
        val rt = api.post(path)
        if (rt.ok()) {
            val pages = rt.one().getJsonObject("data").toModel<Pages>()
            if (pages.data != null) {
                userList = pages.data.toModel<User>()
            } else {
                userList = FXCollections.observableArrayList<User>()
            }
            return true
        }
        return false
    }


}