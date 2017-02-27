package com.daigou.client.view.user

import com.alibaba.fastjson.JSONArray
import com.daigou.client.controller.OrderCtrl
import com.daigou.client.model.OrderModel
import com.daigou.client.view.goods.GoodsPagesView
import com.daigou.core.domain.Goods
import com.daigou.core.domain.Order
import com.daigou.core.domain.OrderDetail
import com.daigou.core.domain.User
import javafx.scene.control.TableRow
import javafx.scene.input.MouseButton
import javafx.stage.Modality
import javafx.stage.StageStyle
import org.controlsfx.control.Notifications
import tornadofx.*


/**
 * Created by wt on 2017/2/24.
 */
class OrderView : Fragment() {
    override val root = Form()
    val orderCtrl: OrderCtrl by inject()
    val userPages = UserPagesView()
    val goodsPages = GoodsPagesView()
    val newOrder = OrderModel()
    val detail = tableview<OrderDetail> {
        column("商品名称", OrderDetail::goodsName).weigthedWidth(1)
        column("数量", OrderDetail::quantity)
        column("总价", OrderDetail::account)
        columnResizePolicy = SmartResize.POLICY
        prefHeight = 200.0
    }

    init {
        title = "新增订单"
        with(userPages) {
            tableView.setRowFactory {
                val row = TableRow<User>()
                row.setOnMouseClicked { e ->
                    if (!row.isEmpty && e.button == MouseButton.PRIMARY && e.clickCount >= 2) {
                        newOrder.userName.value = row.item.userName
                        newOrder.userUuid.value = row.item.uuid
                        closeModal()
                    }
                }
                row
            }
        }
        with(goodsPages) {
            tableView.setRowFactory {
                val row = TableRow<Goods>()
                row.setOnMouseClicked { e ->
                    if (!row.isEmpty && e.button == MouseButton.PRIMARY && e.clickCount >= 2) {
                        val list = detail.items.filter {
                            it.goodsUuid.equals(row.item.uuid)
                        }
                        if (list.isEmpty()) {
                            val new = OrderDetail()
                            new.goodsUuid = row.item.uuid
                            new.goodsName = row.item.name
                            new.account = row.item.price
                            new.quantity = 1
                            detail.items.add(new)
                        } else {
                            list[0].quantity++
                            list[0].account += row.item.price
                        }
                        detail.refresh()
                        closeModal()
                    }
                }
                row
            }
        }
        with(root) {
            prefWidth = 300.0
            newOrder.itemProperty.set(Order())
            fieldset {
                detail
                field("商品：") {
                    button("+") {
                        setOnAction {
                            goodsPages.openModal(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, modalStage)
                        }
                    }
                    button("X") {
                        setOnAction {
                            if (detail.selectedItem != null) {
                                detail.items.remove(detail.selectedItem)
                            }
                        }
                    }
                }
                field("客户：") {
                    textfield {
                        prefWidth = 80.0
                        isEditable = false
                        bind(newOrder.userName)
                        validator {
                            if (it.isNullOrEmpty()) {
                                error("请选择客户")
                            } else {
                                null
                            }
                        }
                    }
                    button("选择") {
                        setOnAction {
                            userPages.openModal(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, modalStage)
                        }
                    }
                }
                field("总价：") {
                    val accountText = textfield {
                        bind(newOrder.account)
                        validator {
                            if (it.isNullOrBlank()) error("手机号不能为空") else null
                        }
                    }
                    button("=") {
                        setOnAction {
                            accountText.text = detail.items.map { it.account }.sum().toString()
                        }
                    }
                }
                field("备注：") {
                    textarea {
                        bind(newOrder.remark)
                        prefHeight = 50.0
                    }
                }
                button("保存") {
                    setOnAction {
                        if (newOrder.commit()) {
                            runAsync {
                                newOrder.detail.value = JSONArray.toJSONString(detail.items)
                                orderCtrl.addOrder(newOrder)
                            } ui { rst ->
                                if (rst) {
                                    closeModal()
                                    Notifications.create().text("保存成功！").owner(primaryStage).showWarning()
                                } else {
                                    Notifications.create().text("保存失败！").owner(this).showError()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}