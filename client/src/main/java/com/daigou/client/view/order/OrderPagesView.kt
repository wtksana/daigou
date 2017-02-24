package com.daigou.client.view.order

import com.daigou.client.controller.OrderCtrl
import com.daigou.client.view.PagesTool
import com.daigou.client.view.user.OrderView
import com.daigou.common.util.DateUtil
import com.daigou.core.domain.Order
import javafx.scene.control.TableView
import javafx.scene.layout.AnchorPane
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*

/**
 * Created by wt on 2017/2/24.
 */
class OrderPagesView : Fragment() {
    override val root = AnchorPane()
    val orderCtrl: OrderCtrl by inject()
    val tableView = TableView<Order>()
    val pagesTool = PagesTool(orderCtrl, tableView)

    init {
        title = "订单列表"
        with(tableView) {
            column("客户", Order::userName).weigthedWidth(1)
            column("订单总价", Order::account)
            column("完成时间", Order::doneTime).cellFormat {
                text = DateUtil.dateStr4(it)
            }
            column("添加时间", Order::createTime).cellFormat {
                text = DateUtil.dateStr4(it)
            }
            anchorpaneConstraints {
                bottomAnchor = 40.0
                leftAnchor = 0.0
                rightAnchor = 0.0
                topAnchor = 0.0
            }
            columnResizePolicy = SmartResize.POLICY
        }
        with(pagesTool) {
            button("+") {
                setOnAction {
                    OrderView().openModal(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, primaryStage)
                }
            }
        }
        this += tableView
        this += pagesTool
    }
}