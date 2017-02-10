package com.daigou.client.controller

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

    fun getUserList(page: Int): Boolean {
        val path = "/user/userList.html?page=$page&row=1"
        val rt = api.post(path)
        if (rt.ok()) {
            userList = rt.one().getJsonArray("data").toModel<User>()
            return true
        }
        return false
    }
}