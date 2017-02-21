package com.daigou.client.view.user

import com.daigou.client.controller.UserCtrl
import com.daigou.client.model.UserModel
import com.daigou.client.view.PagesTool
import com.daigou.common.util.DateUtil
import com.daigou.core.domain.User
import javafx.scene.control.TableView
import javafx.scene.input.ClipboardContent
import javafx.scene.input.TransferMode
import javafx.scene.paint.Color
import javafx.stage.Modality
import javafx.stage.StageStyle
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
    val tableView = TableView<User>()
    val pagesTool = PagesTool(userCtrl, tableView)
    val selectedUser = UserModel()

    init {
        initTableView()
        initDetailForm()
        with(pagesTool) {
            button("+") {
                setOnAction {
                    UserView().openModal(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, primaryStage)
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
            bindSelected(selectedUser)
//            setOnDragDetected { e ->
//                val db = startDragAndDrop(TransferMode.COPY)
//                val content = ClipboardContent()
//                content.putString(selectedUser.mobile.value)
//                db.setContent(content)
//                e.consume()
//            }
        }
    }

    fun initDetailForm() {
        with(detailForm) {
            fieldset {
                field("微信号：") {
                    textfield {
                        bind(selectedUser.wechat)
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
//                        setOnDragOver { e ->
//                            if (e.dragboard.hasString()) {
//                                e.acceptTransferModes(TransferMode.COPY)
//                            }
//                            e.consume()
//                        }
//                        setOnDragEntered { e ->
//                            if (e.dragboard.hasString()) {
//                                this.style {
//                                    backgroundColor += Color.SKYBLUE
//                                }
//                            }
//                            e.consume()
//                        }
//                        setOnDragExited { e ->
//                            if (e.dragboard.hasString()) {
//                                this.style {
//
//                                }
//                            }
//                            e.consume()
//                        }
//                        setOnDragDropped { e ->
//                            val db = e.dragboard
//                            if (db.hasString()) {
//                                text = db.string
//                            }
//                            e.isDropCompleted = true
//                            e.consume()
//                        }
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