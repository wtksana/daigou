package com.daigou.client.view

import com.daigou.client.controller.OperatorCtrl
import com.daigou.client.view.user.UserListView
import javafx.scene.layout.BorderPane
import tornadofx.View
import tornadofx.menu
import tornadofx.menubar
import tornadofx.menuitem

/**
 * Created by wt on 2017/2/9.
 */
class MainView : View("代购系统") {
    val operatorCtrl: OperatorCtrl by inject()
    val userListView: UserListView by inject()
    override val root: BorderPane by fxml("/fxml/main.fxml")

    init {
        primaryStage.isResizable = false
        with(root) {
            top = menubar {
                menu("客户") {
                    menuitem("客户列表").setOnAction {
                        if (center != userListView.root) {
                            center = userListView.root
                        }
                    }
                    menuitem("添加客户").setOnAction { }
                }
            }

        }
    }
}