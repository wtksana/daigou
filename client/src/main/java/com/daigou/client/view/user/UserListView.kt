package com.daigou.client.view.user

import com.daigou.client.controller.UserCtrl
import com.daigou.client.model.UserModel
import org.controlsfx.control.Notifications
import tornadofx.*

/**
 * Created by wt on 2017/2/10.
 */
class UserListView : View() {
    override val root = anchorpane {
        prefHeight = 575.0
        prefWidth = 800.0
    }
    val userCtrl: UserCtrl by inject()
    val detailForm = Form()
    val pagesView = UserPagesView()
    val selectedUser = UserModel()

    init {
        initPageView()
        initDetailForm()
        with(root) {
            this += pagesView
            this += detailForm
        }
    }

    fun initPageView() {
        with(pagesView) {
            root.anchorpaneConstraints {
                bottomAnchor = 0.0
                leftAnchor = 0.0
                rightAnchor = 300.0
                topAnchor = 0.0
            }
            tableView.bindSelected(selectedUser)
        }
    }

    fun initDetailForm() {
        with(detailForm) {
            fieldset {
                field("微信号：") {
                    textfield {
                        bind(selectedUser.userName)
                        validator {
                            if (it.isNullOrBlank()) error("微信号不能为空") else null
                        }
                    }
                }
                field("姓名：") {
                    textfield {
                        bind(selectedUser.realName)
                    }
                }
                field("手机号：") {
                    textfield {
                        bind(selectedUser.mobile)
                        validator {
                            if (it.isNullOrBlank()) error("手机号不能为空") else null
                        }
                    }
                }
                field("地址：") {
                    textfield {
                        bind(selectedUser.address)
                    }
                }
                field("备注：") {
                    textarea {
                        bind(selectedUser.remark)
                    }
                }
                field("推荐人：") {
                    textfield {
                        promptText = "手机号"
                        bind(selectedUser.inviteUser)
                    }
                }
                button("保存") {
                    setOnAction {
                        if (selectedUser.commit() && !selectedUser.uuid.value.isNullOrEmpty()) {
                            runAsync {
                                userCtrl.editUser(selectedUser)
                            } ui { rst ->
                                if (rst) {
                                    Notifications.create().text("保存成功！").owner(this).showWarning()
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