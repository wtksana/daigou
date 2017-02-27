package com.daigou.client.view.goods

import com.daigou.client.controller.GoodsCtrl
import com.daigou.client.model.GoodsModel
import com.daigou.client.model.GoodsTypeModel
import com.daigou.core.domain.GoodsType
import org.controlsfx.control.Notifications
import tornadofx.*
import java.util.*

/**
 * Created by wt on 2017/2/16.
 */
class GoodsTypeView(var t: GoodsModel) : Fragment() {
    val goodsCtrl: GoodsCtrl by inject()
    val selectedType = GoodsTypeModel()

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
            goodsCtrl.editGoodsType(it.uuid, it.type.orEmpty())
        }
        bindSelected(selectedType)
        columnResizePolicy = SmartResize.POLICY
        runAsync {
            goodsCtrl.getGoodsTypeList()
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
                newType.uuid = UUID.randomUUID().toString().replace("-", "")
                runAsync {
                    goodsCtrl.addGoodsType(newType)
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
                t.typeUuid.value = selectedType.uuid.value
                t.type.value = selectedType.type.value
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