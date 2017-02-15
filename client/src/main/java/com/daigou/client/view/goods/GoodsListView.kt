package com.daigou.client.view.user

import com.daigou.client.controller.GoodsCtrl
import com.daigou.client.model.GoodsModel
import com.daigou.client.view.PagesTool
import com.daigou.common.util.DateUtil
import com.daigou.core.domain.Goods
import javafx.scene.control.TableView
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
    val ctrl: GoodsCtrl by inject()
    val detailForm = Form()
    //    val userTable: TableView<User> by fxid("userTable")
    val tableView = TableView<Goods>()
    val pagesTool = PagesTool(ctrl, tableView)
    val selectedGoods = GoodsModel()

    init {
        initTableView()
        initDetailForm()
        with(pagesTool) {
            button("+") {
                setOnAction {
                    GoodsView().openModal(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, primaryStage)
                }
            }
            with(root) {
                anchorpaneConstraints {
                    rightAnchor = 301.0
                }
            }
        }
        with(root) {
            this += tableView
            this += detailForm
            this += pagesTool
        }
    }

    fun initTableView() {
        with(tableView) {
            column("名称", Goods::name).weigthedWidth(1)
            column("类别", Goods::type)
            column("售价", Goods::price)
            column("进价", Goods::bid)
//            column("地址", User::addressProperty)
//            column("备注", User::remarkProperty)
            column("添加时间", Goods::createTime).cellFormat {
                text = DateUtil.dateStr4(it)
            }
            anchorpaneConstraints {
                bottomAnchor = 0.0
                leftAnchor = 0.0
                rightAnchor = 300.0
                topAnchor = 0.0
            }
            columnResizePolicy = SmartResize.POLICY
            bindSelected(selectedGoods)
        }
    }

    fun initDetailForm() {
        with(detailForm) {
            fieldset {
                field("类别：") {
                    choicebox<String> {
                        runAsync {
                            ctrl.getGoodsTypes()
                        } ui { rst ->
                            rst.forEach {
                                items.add(it)
                            }
                        }
                        bind(selectedGoods.type)
                    }
                }
                field("名称：") {
                    textfield {
                        bind(selectedGoods.name)
                    }
                }
                field("售价：") {
                    textfield {
                        bind(selectedGoods.price)
                        validator {
                            if (it.isNullOrBlank()) error("") else null
                        }
                    }
                }
                field("进价：") {
                    textfield {
                        bind(selectedGoods.bid)
                    }
                }
                field("备注：") {
                    textarea {
                        bind(selectedGoods.remark)
                    }
                }
                button("保存") {
                    setOnAction {
                        if (selectedGoods.commit() && !selectedGoods.uuid.value.isNullOrEmpty()) {
                            runAsync {
                                ctrl.editGoods(selectedGoods)
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

            anchorpaneConstraints {
                leftAnchor = 530.0
                rightAnchor = 20.0
                topAnchor = 50.0
            }
        }
    }

}