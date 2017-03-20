package com.daigou.client.view

import com.daigou.client.controller.OperatorCtrl
import com.daigou.client.view.system.ConfigView
import com.daigou.client.view.user.GoodsListView
import com.daigou.client.view.user.OrderListView
import com.daigou.client.view.user.UserListView
import javafx.scene.text.Text
import tornadofx.*

/**
 * Created by wt on 2017/2/9.
 */
class MainView : View("代购系统") {
    val operatorCtrl: OperatorCtrl by inject()
    val userListView: UserListView by inject()
    val goodsListView: GoodsListView by inject()
    val orderListView: OrderListView by inject()
    val configView: ConfigView by inject()

    override val root = borderpane {
        minHeight = 600.0
        minWidth = 800.0
    }
    val user = Text("客户")
    val order = Text("订单")
    val goods = Text("商品")
    val configs = Text("设置")

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
                    if (center != orderListView.root) {
                        center = orderListView.root
                    }
                }
            }
            with(goods) {
                setOnMouseClicked {
                    if (center != goodsListView.root) {
                        center = goodsListView.root
                    }
                }
            }
            with(configs){
                setOnMouseClicked {
                    if (center != configView.root) {
                        center = configView.root
                    }
                }
            }
            top = menubar {
                menu().graphic = user
                menu().graphic = goods
                menu().graphic = order
                menu().graphic = configs
            }
        }
    }
}