package com.daigou.client.view.user

import com.daigou.client.controller.UserCtrl
import com.daigou.client.model.UserModel
import com.daigou.core.domain.User
import org.controlsfx.control.Notifications
import tornadofx.*

/**
 * Created by wt on 2017/2/12.
 */
class UserView : Fragment() {
    override val root = Form()
    val ctrl: UserCtrl by inject()
    val newUser = UserModel()

    init {
        title = "新增"
        with(root) {
            newUser.itemProperty.set(User())
            fieldset {
                field("微信号：") {
                    textfield {
                        bind(newUser.wechat)
                        validator {
                            if (it.isNullOrBlank()) error("微信号不能为空") else null
                        }
                    }
                }
                field("姓名：") {
                    textfield {
                        bind(newUser.realName)
                    }
                }
                field("手机号：") {
                    textfield {
                        bind(newUser.mobile)
                        validator {
                            if (it.isNullOrBlank()) error("手机号不能为空") else null
                        }
                    }
                }
                field("地址：") {
                    textfield {
                        bind(newUser.address)
                    }
                }
                field("备注：") {
                    textarea {
                        bind(newUser.remark)
                    }
                }
                field("推荐人：") {
                    textfield {
                        promptText = "手机号"
                        bind(newUser.inviteUser)
                    }
                }
                button("保存") {
                    setOnAction {
                        if (newUser.commit()) {
                            runAsync {
                                ctrl.addUser(newUser)
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