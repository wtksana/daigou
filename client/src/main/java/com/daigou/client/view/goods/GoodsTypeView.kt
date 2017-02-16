package com.daigou.client.view.goods

import com.daigou.client.controller.GoodsCtrl
import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.cell.TextFieldListCell
import tornadofx.*

/**
 * Created by wt on 2017/2/16.
 */
class GoodsTypeView : View() {
    val ctrl: GoodsCtrl by inject()
    val selectType = SimpleStringProperty()
    override val root = borderpane {
        prefHeight = 400.0
        prefWidth = 300.0
    }

    init {
        title = "类别管理"
        with(root) {
            top = listview<String> {
                isEditable = true
                cellFactory = TextFieldListCell.forListView()
                setOnEditCommit {
                    println(selectType.value)
                    items[it.index] = it.newValue
                }
                bindSelected(selectType)
//                setOnEditCancel {
//                    println(2)
//                }
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