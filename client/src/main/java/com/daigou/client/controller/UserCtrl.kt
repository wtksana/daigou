package com.daigou.client.controller

import com.daigou.client.model.Pages
import com.daigou.client.model.User
import com.daigou.client.model.UserModel
import javafx.collections.FXCollections
import tornadofx.toModel

/**
 * Created by wt on 2017/2/10.
 */
class UserCtrl : BaseCtrl<User>() {

    val selectedUser = UserModel()
    override fun getList(page: Int, row: Int, option: String): Boolean {
        val path = "/user/userList.html?page=$page&row=$row&option=$option"
        val rt = api.post(path)
        if (rt.ok()) {
            pages = rt.one().getJsonObject("data").toModel<Pages>()
            if (pages.data != null) {
                list = pages.data.toModel<User>()
            } else {
                list = FXCollections.observableArrayList<User>()
            }
            return true
        }
        return false
    }


}