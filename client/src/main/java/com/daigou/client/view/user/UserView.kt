package com.daigou.client.view.user

import com.daigou.client.controller.UserCtrl
import com.daigou.client.model.UserModel
import com.daigou.core.domain.User
import tornadofx.*

/**
 * Created by wt on 2017/2/12.
 */
class UserView : Fragment() {
    override val root = Form()
    val userCtrl: UserCtrl by inject()
    val newUser = UserModel()

    init {
        title = "新增"
        with(root) {
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
                button("保存") {
                    setOnAction {
                        if (newUser.commit()) {
                            userCtrl.addUser(newUser)
                            newUser.itemProperty.set(User())
                        }
                    }
                }
            }
        }
    }

}