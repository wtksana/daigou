package com.daigou.client.view.goods

import com.daigou.client.controller.GoodsCtrl
import com.daigou.client.model.GoodsTypeModel
import com.daigou.core.domain.GoodsType
import javafx.scene.control.TextField
import org.controlsfx.control.Notifications
import tornadofx.*

/**
 * Created by wt on 2017/2/16.
 */
class GoodsTypeView(var t: TextField) : Fragment() {
    val ctrl: GoodsCtrl by inject()
    val selectedType = GoodsTypeModel()
    val newType = GoodsType()

    override val root = borderpane {
        prefHeight = 300.0
        prefWidth = 200.0
    }

    val typeTable = tableview<GoodsType> {
        isEditable = true
        column("类别", GoodsType::type).useTextField {
            it.rowValue.type = it.newValue
        }
        onEditCommit {
            selectedType.commit()
            ctrl.editGoodsType(it.uuid ?: "", it.type ?: "")
        }
        bindSelected(selectedType)
        columnResizePolicy = SmartResize.POLICY
        runAsync {
            ctrl.getGoodsTypeList()
        } ui { rst ->
            items = rst.observable()
        }
    }

    val toolbox = hbox {
        val type = textfield {

        }
        button("+") {
            disableProperty().bind(type.textProperty().isEmpty)
            setOnAction {
                val newType = GoodsType()
                newType.type = type.text
                runAsync {
                    ctrl.addGoodsType(type.text)
                } ui { rst ->
                    if (rst) {
                        typeTable.items.add(newType)
                        type.text = ""
                    } else {
                        Notifications.create().text("添加失败！").owner(this).showError()
                    }
                }
            }
        }
        button("√") {
            disableProperty().bind(selectedType.empty)
            setOnAction {
                t.text = selectedType.type.value
                closeModal()
            }
        }
    }

    init {
        title = "类别管理"
        with(root) {
            center = typeTable
            bottom = toolbox
        }
    }
}