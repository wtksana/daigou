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
    val userCtrl: UserCtrl by inject()
    val newUser = UserModel()

    init {
        title = "新增客户"
        with(root) {
            prefWidth = 300.0
            newUser.itemProperty.set(User())
            fieldset {
                field("微信号：") {
                    textfield {
                        bind(newUser.userName)
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
                        prefHeight = 50.0
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
                                userCtrl.addUser(newUser)
                            } ui { rst ->
                                if (rst) {
                                    close()
                                    Notifications.create().text("操作成功！").owner(primaryStage).showWarning()
                                } else {
                                    Notifications.create().text("操作失败！").owner(this).showError()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}