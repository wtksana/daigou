package com.daigou.client.view.user

import com.daigou.client.controller.UserCtrl
import com.daigou.client.model.User
import com.daigou.client.view.PagesTool
import javafx.scene.control.ContextMenu
import javafx.scene.control.TableView
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
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
    val userDetail: UserDetailView by inject()
    val userTable: TableView<User> by fxid("userTable")
    val row = 20
    val pagesTool = PagesTool()
    val edit = ContextMenu()

    init {
        with(edit) {
            menuitem("编辑") {
                setOnAction {
                    if (userCtrl.selectedUser.uuid.value != null) {
                        userDetail.openWindow(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, ownerWindow)
                    }
                }
            }
        }
        with(userTable) {
            column("微信号", User::wechatProperty)
            column("姓名", User::realNameProperty)
            column("手机号", User::mobileProperty)
            column("地址", User::addressProperty)
            column("备注", User::remarkProperty).weigthedWidth(1.0)
            column("添加时间", User::createTimeProperty)
            anchorpaneConstraints {
                bottomAnchor = 0.0
                leftAnchor = 0.0
                rightAnchor = 0.0
                topAnchor = 0.0
            }
            columnResizePolicy = SmartResize.POLICY
            bindSelected(userCtrl.selectedUser)
            setOnMouseClicked { e ->
                if (e.button == MouseButton.SECONDARY) {
                    edit.show(userTable, e.screenX, e.screenY)
                } else {
                    edit.hide()
                }
            }
        }
        with(pagesTool) {
            page.setOnKeyReleased { e ->
                if (e.code == KeyCode.ENTER) {
                    userList(page.text.toInt(), 20)
                }
            }
        }
        with(root) {
            this += pagesTool
        }
        userList(1, row)

    }


    fun userList(page: Int, row: Int) {
        runAsync {
            userCtrl.getUserList(page, row)
        } ui { rst ->
            if (rst) {
                userTable.items = userCtrl.userList
                userTable.refresh()
            }
        }

    }
}