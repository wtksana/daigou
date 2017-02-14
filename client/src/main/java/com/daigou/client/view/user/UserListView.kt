package com.daigou.client.view.user

import com.daigou.client.controller.UserCtrl
import com.daigou.client.view.PagesTool
import com.daigou.common.util.DateUtil
import com.daigou.core.domain.User
import javafx.scene.control.TableView
import javafx.scene.layout.AnchorPane
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*

/**
 * Created by wt on 2017/2/10.
 */
class UserListView : View() {
    override val root: AnchorPane by fxml("/fxml/user/userList.fxml")
    val userCtrl: UserCtrl by inject()
    val userDetail = Form()
    //    val userTable: TableView<User> by fxid("userTable")
    val userTable = TableView<User>()
    val pagesTool = PagesTool(userCtrl, userTable)

    init {
        initUserTable()
        initUserDetail()
        with(pagesTool) {
            button("＋") {
                setOnAction {
                    UserView().openWindow(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, primaryStage)
                }
            }
            with(root) {
                anchorpaneConstraints {
                    rightAnchor = 301.0
                }
            }
        }
        with(root) {
            this += userTable
            this += userDetail
            this += pagesTool
        }
    }

    fun initUserTable() {
        with(userTable) {
            column("微信号", User::wechat).weigthedWidth(1)
            column("姓名", User::realName).weigthedWidth(1)
            column("手机号", User::mobile)
//            column("地址", User::addressProperty)
//            column("备注", User::remarkProperty)
            column("添加时间", User::createTime).cellFormat {
                text = DateUtil.dateStr4(it)
            }
            anchorpaneConstraints {
                bottomAnchor = 0.0
                leftAnchor = 0.0
                rightAnchor = 300.0
                topAnchor = 0.0
            }
            columnResizePolicy = SmartResize.POLICY
            bindSelected(userCtrl.selectedUser)
        }
    }

    fun initUserDetail() {
        with(userDetail) {
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

            anchorpaneConstraints {
                leftAnchor = 530.0
                rightAnchor = 20.0
                topAnchor = 50.0
            }
        }
    }

}