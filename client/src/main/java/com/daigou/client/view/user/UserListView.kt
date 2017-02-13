package com.daigou.client.view.user

import com.daigou.client.controller.UserCtrl
import com.daigou.client.model.User
import com.daigou.client.view.PagesTool
import javafx.scene.control.Button
import javafx.scene.control.TableView
import javafx.scene.input.KeyCode
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
    val userDetail = UserDetailView()
    //    val userTable: TableView<User> by fxid("userTable")
    val userTable = TableView<User>()
    val pagesTool = PagesTool(userCtrl,userTable)
    val add = Button("添加")

    init {
        with(add) {
            setOnAction {
                UserDetailView().openWindow(StageStyle.DECORATED, Modality.WINDOW_MODAL, true, contextMenu)
            }
        }
        with(userTable) {
            column("微信号", User::wechatProperty).weigthedWidth(1)
            column("姓名", User::realNameProperty).weigthedWidth(1)
            column("手机号", User::mobileProperty)
//            column("地址", User::addressProperty)
//            column("备注", User::remarkProperty)
            column("添加时间", User::createTimeProperty)
            anchorpaneConstraints {
                bottomAnchor = 0.0
                leftAnchor = 0.0
                rightAnchor = 300.0
                topAnchor = 0.0
            }
            columnResizePolicy = SmartResize.POLICY
            bindSelected(userCtrl.selectedUser)

        }
        with(userDetail.root) {
            anchorpaneConstraints {
                leftAnchor = 530.0
                rightAnchor = 20.0
                topAnchor = 50.0
            }
        }
        with(pagesTool) {
            this += add
        }
        with(root) {
            this += userTable
            this += userDetail
            this += pagesTool
        }
    }

}