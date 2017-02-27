package com.daigou.client.view.user

import com.alibaba.fastjson.JSONArray
import com.daigou.client.controller.OrderCtrl
import com.daigou.client.model.OrderModel
import com.daigou.client.view.order.OrderPagesView
import com.daigou.core.domain.OrderDetail
import org.apache.commons.codec.binary.Base64
import org.controlsfx.control.Notifications
import tornadofx.*

/**
 * Created by wt on 2017/2/24.
 */
class OrderListView : View() {
    override val root = anchorpane {
        prefHeight = 575.0
        prefWidth = 800.0
    }
    val orderCtrl: OrderCtrl by inject()
    val detailForm = Form()
    val pagesView = OrderPagesView()
    val selectedOrder = OrderModel()

    init {
        initTableView()
        initDetailForm()
        with(root) {
            this += pagesView
            this += detailForm
        }
    }

    fun initTableView() {
        with(pagesView) {
            root.anchorpaneConstraints {
                bottomAnchor = 0.0
                leftAnchor = 0.0
                rightAnchor = 300.0
                topAnchor = 0.0
            }
            tableView.bindSelected(selectedOrder)
        }
    }

    fun initDetailForm() {
        with(detailForm) {
            fieldset {
                val detail = tableview<OrderDetail> {
                    column("商品名称", OrderDetail::goodsName).weigthedWidth(1)
                    column("数量", OrderDetail::quantity)
                    column("总价", OrderDetail::account)
                    columnResizePolicy = SmartResize.POLICY
                    prefHeight = 200.0
                    selectedOrder.detail.addListener { value, old, new ->
                        if (!new.isNullOrBlank()) {
                            items = JSONArray.parseArray(kotlin.text.String(Base64.decodeBase64(new.replace("\n", "").replace(" ", "+"))), OrderDetail::class.java).observable()
                        }
                    }
                }
                field("总价：") {
                    val accountText = textfield {
                        bind(selectedOrder.account)
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
                        bind(selectedOrder.remark)
                        prefHeight = 50.0
                    }
                }
                field("操作：") {
                    button("保存") {
                        setOnAction {
                            if (selectedOrder.commit() && !selectedOrder.uuid.value.isNullOrEmpty()) {
                                runAsync {
                                    orderCtrl.editOrder(selectedOrder)
                                } ui { rst ->
                                    if (rst) {
                                        closeModal()
                                        Notifications.create().text("操作成功！").owner(primaryStage).showWarning()
                                    } else {
                                        Notifications.create().text("操作失败！").owner(this).showError()
                                    }
                                }
                            }
                        }
                    }
                    button("删除") {
                        setOnAction {
                            if (!selectedOrder.uuid.value.isNullOrEmpty()) {
                                runAsync {
                                    orderCtrl.deleteOrder(selectedOrder.uuid.value)
                                } ui { rst ->
                                    if (rst) {
                                        Notifications.create().text("操作成功！").owner(this).showWarning()
                                    } else {
                                        Notifications.create().text("操作失败！").owner(this).showError()
                                    }
                                    pagesView.pagesTool.getList()
                                }
                            }
                        }
                    }
                }
            }
            anchorpaneConstraints {
                leftAnchor = 530.0
                rightAnchor = 20.0
                topAnchor = 50.0
            }
        }
    }

}