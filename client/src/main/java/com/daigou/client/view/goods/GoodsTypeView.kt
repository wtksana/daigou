package com.daigou.client.view.goods

import com.daigou.client.controller.GoodsCtrl
import com.daigou.core.domain.GoodsType
import tornadofx.*

/**
 * Created by wt on 2017/2/16.
 */
class GoodsTypeView : View() {
    val ctrl: GoodsCtrl by inject()
    override val root = borderpane {
        prefHeight = 400.0
        prefWidth = 300.0
    }

    init {
        title = "类别管理"
        with(root) {
            center = tableview<GoodsType> {
                columnResizePolicy = SmartResize.POLICY
                enableCellEditing()
                column("类别", GoodsType::type).useTextField {
                    it.rowValue.type = it.newValue
                }
                onEditCommit {
                    ctrl.editGoodsType(it.uuid ?: "", it.type ?: "")
                }
                runAsync {
                    ctrl.getGoodsTypeList()
                } ui { rst ->
                    items = rst.observable()
                    refresh()
                }
            }
        }
    }
}