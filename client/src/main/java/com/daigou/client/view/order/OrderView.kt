package com.daigou.client.view.user

import com.daigou.client.controller.OrderCtrl
import com.daigou.client.model.OrderModel
import com.daigou.client.view.goods.GoodsPagesView
import com.daigou.core.domain.Goods
import com.daigou.core.domain.Order
import com.daigou.core.domain.OrderDetail
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
    val ctrl: OrderCtrl by inject()
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
        title = "新增"
        with(userPages) {
            tableView.selectionModel.selectedItemProperty().addListener { value, old, new ->
                if (new != null) {
                    newOrder.userName.value = new.userName
                    newOrder.userUuid.value = new.uuid
                }
            }
        }
        with(goodsPages) {
            tableView.setRowFactory {
                val row = TableRow<Goods>()
                row.setOnMouseClicked { e ->
                    if (!row.isEmpty && e.button == MouseButton.PRIMARY && e.clickCount >= 2) {
                        val new = OrderDetail()
                        new.goodsUuid = row.item.uuid
                        new.goodsName = row.item.name
                        new.account = row.item.price
                        new.quantity = 1
                        detail.items.add(new)
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
                field("商品：") {
                    button("+") {
                        setOnAction {
                            goodsPages.openModal(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, modalStage)
                        }
                    }
                    button("X") {
                        setOnAction {

                        }
                    }
                }
                detail
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
                    textfield {
                        bind(newOrder.account)
                        validator {
                            if (it.isNullOrBlank()) error("手机号不能为空") else null
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
                                ctrl.addOrder(newOrder)
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