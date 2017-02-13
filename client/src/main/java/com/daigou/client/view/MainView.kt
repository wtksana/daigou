package com.daigou.client.view

import com.daigou.client.controller.OperatorCtrl
import com.daigou.client.view.user.UserListView
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.scene.text.Text
import tornadofx.View
import tornadofx.menu
import tornadofx.menubar

/**
 * Created by wt on 2017/2/9.
 */
class MainView : View("代购系统") {
    val operatorCtrl: OperatorCtrl by inject()
    val userListView: UserListView by inject()
    override val root: BorderPane by fxml("/fxml/main.fxml")
    val user = Text("客户")
    val order = Text("订单")
    val goods = Text("商品")

    init {
        primaryStage.isResizable = false
        with(root) {
            with(user) {
                setOnMouseClicked {
                    if (center != userListView.root) {
                        center = userListView.root
                    }
                }
            }
            with(order) {
                setOnMouseClicked {
                    if (center != userListView.root) {
                        center = userListView.root
                    }
                }
            }
            with(goods) {
                setOnMouseClicked {
                    if (center != userListView.root) {
                        center = userListView.root
                    }
                }
            }
            top = menubar {
                menu().graphic = user
                menu().graphic = order
                menu().graphic = goods
            }
        }
    }
}