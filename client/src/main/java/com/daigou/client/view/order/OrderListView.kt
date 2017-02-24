package com.daigou.client.view.user

import com.daigou.client.controller.OrderCtrl
import com.daigou.client.model.OrderModel
import com.daigou.client.view.order.OrderPagesView
import com.daigou.core.domain.OrderDetail
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
                tableview<OrderDetail> {
                    column("商品名称", OrderDetail::goodsName).weigthedWidth(1)
                    column("数量", OrderDetail::quantity)
                    column("总价", OrderDetail::account)
                    columnResizePolicy = SmartResize.POLICY
                    selectedOrder.detail.addListener { value, old, new ->
                        items = new.orEmpty().observable()
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