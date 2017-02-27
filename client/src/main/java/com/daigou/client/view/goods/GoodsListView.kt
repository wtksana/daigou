package com.daigou.client.view.user

import com.daigou.client.controller.GoodsCtrl
import com.daigou.client.model.GoodsModel
import com.daigou.client.view.goods.GoodsPagesView
import com.daigou.client.view.goods.GoodsTypeView
import javafx.stage.Modality
import javafx.stage.StageStyle
import org.controlsfx.control.Notifications
import tornadofx.*

/**
 * Created by wt on 2017/2/10.
 */
class GoodsListView : View() {
    override val root = anchorpane {
        prefHeight = 575.0
        prefWidth = 800.0
    }
    val goodsCtrl: GoodsCtrl by inject()
    val detailForm = Form()
    val pagesView: GoodsPagesView by inject()
    val selectedGoods = GoodsModel()
    val reg = "(^[1-9]\\d*(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)".toRegex()

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
            tableView.bindSelected(selectedGoods)
        }
    }

    fun initDetailForm() {
        with(detailForm) {
            fieldset {
                field("类别：") {
                    textfield {
                        prefWidth = 80.0
                        isEditable = false
                        bind(selectedGoods.type)
                        validator {
                            if (it.isNullOrEmpty()) {
                                error("请选择类型")
                            } else {
                                null
                            }
                        }
                    }
                    button("选择") {
                        setOnAction {
                            GoodsTypeView(selectedGoods).openModal(StageStyle.DECORATED, Modality.WINDOW_MODAL, false, primaryStage)
                        }
                    }
                }
                field("名称：") {
                    textfield {
                        bind(selectedGoods.name)
                        validator {
                            if (it.isNullOrEmpty()) {
                                error("请输入名称")
                            } else {
                                null
                            }
                        }
                    }
                }
                field("售价：") {
                    textfield {
                        bind(selectedGoods.price)
                        validator {
                            if (it != null && !it.matches(reg)) {
                                error("请输入正常的价格（小数点后最多两位）")
                            } else {
                                null
                            }
                        }
                    }
                }
                field("专柜价：") {
                    textfield {
                        bind(selectedGoods.counter)
                        validator {
                            if (it != null && !it.matches(reg)) {
                                error("请输入正常的价格（小数点后最多两位）")
                            } else {
                                null
                            }
                        }
                    }
                }
                field("进价：") {
                    textfield {
                        bind(selectedGoods.bid)
                        validator {
                            if (it != null && !it.matches(reg)) {
                                error("请输入正常的价格（小数点后最多两位）")
                            } else {
                                null
                            }
                        }
                    }
                }
                field("备注：") {
                    textarea {
                        bind(selectedGoods.remark)
                    }
                }
                field("操作：") {
                    button("保存") {
                        setOnAction {
                            if (selectedGoods.commit() && !selectedGoods.uuid.value.isNullOrEmpty()) {
                                runAsync {
                                    goodsCtrl.editGoods(selectedGoods)
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
                            if (!selectedGoods.uuid.value.isNullOrEmpty()) {
                                runAsync {
                                    goodsCtrl.deleteGoods(selectedGoods.uuid.value)
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