package com.daigou.client.view.user

import com.daigou.client.controller.GoodsCtrl
import com.daigou.client.model.GoodsModel
import com.daigou.core.domain.Goods
import org.controlsfx.control.Notifications
import tornadofx.*

/**
 * Created by wt on 2017/2/12.
 */
class GoodsView : Fragment() {
    override val root = Form()
    val ctrl: GoodsCtrl by inject()
    val newGoods = GoodsModel()
    val reg = "(^[1-9]\\d*(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)".toRegex()

    init {
        title = "新增"
        with(root) {
            newGoods.itemProperty.set(Goods())
            fieldset {
                field("类别：") {
                    choicebox<String> {
                        runAsync {
                            ctrl.getGoodsTypeList()
                        } ui { rst ->
                            items.setAll(rst.map { it.type })
                        }
                        bind(newGoods.type)
                    }
                }
                field("名称：") {
                    textfield {
                        bind(newGoods.name)
                    }
                }
                field("售价：") {
                    textfield {
                        bind(newGoods.price)
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
                        bind(newGoods.counter)
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
                        bind(newGoods.bid)
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
                        bind(newGoods.remark)
                    }
                }
                button("保存") {
                    setOnAction {
                        if (newGoods.commit()) {
                            runAsync {
                                ctrl.addGoods(newGoods)
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