package com.daigou.client.view

import com.daigou.client.controller.UserCtrl
import com.daigou.client.model.User
import javafx.scene.layout.AnchorPane
import tornadofx.*

/**
 * Created by wt on 2017/2/10.
 */
class UserView : View() {
    override val root: AnchorPane by fxml("/fxml/user/userList.fxml")
    val userCtrl: UserCtrl by inject()

    init {
        runAsync {
            userCtrl.getUserList(1)
        } ui { rst ->
            if (rst) {
                tableview(userCtrl.userList) {
                    column("微信号", User::wechatProperty).minWidth = 120.0
                    column("姓名", User::realName).minWidth = 120.0
                }.anchorpaneConstraints {
                    bottomAnchor = 0.0
                    leftAnchor = 0.0
                    rightAnchor = 0.0
                    topAnchor = 0.0
                }
            }
        }

    }
}