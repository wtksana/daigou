package com.daigou.client.view.user

import com.daigou.client.controller.UserCtrl
import tornadofx.*

/**
 * Created by wt on 2017/2/12.
 */
class UserDetailView : Fragment() {
    override val root = Form()
    val userCtrl: UserCtrl by inject()

    init {
        title = "编辑"
        with(root) {
            fieldset {
                field("微信号：") {
                    textfield {
                        bind(userCtrl.selectedUser.wechat)
                        validator {
                            if (it.isNullOrBlank()) error("微信号不能为空") else null
                        }
                    }
                }
                field("姓名：") {
                    textfield {
                        bind(userCtrl.selectedUser.realName)
                    }
                }
                field("手机号：") {
                    textfield {
                        bind(userCtrl.selectedUser.mobile)
                        validator {
                            if (it.isNullOrBlank()) error("手机号不能为空") else null
                        }
                    }
                }
                field("地址：") {
                    textfield {
                        bind(userCtrl.selectedUser.address)
                    }
                }
                field("备注：") {
                    textarea {
                        bind(userCtrl.selectedUser.remark)
                    }
                }
                button("保存") {
                    setOnAction {
                        userCtrl.selectedUser.commit()
                    }
                }
            }
        }
    }

}