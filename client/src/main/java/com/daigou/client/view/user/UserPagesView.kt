package com.daigou.client.view.user

import com.daigou.client.controller.UserCtrl
import com.daigou.client.util.exportExcel
import com.daigou.client.view.PagesTool
import com.daigou.common.util.DateUtil
import com.daigou.core.domain.Entity
import com.daigou.core.domain.User
import com.jfoenix.controls.JFXSpinner
import javafx.scene.control.TableView
import javafx.scene.layout.AnchorPane
import javafx.stage.FileChooser
import org.controlsfx.control.Notifications
import tornadofx.*
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by wt on 2017/2/24.
 */
class UserPagesView : Fragment() {
    override val root = AnchorPane()
    val userCtrl: UserCtrl by inject()
    val tableView = TableView<User>()
    val pagesTool = PagesTool(userCtrl, tableView, Entity.User)

    init {
        title = "用户列表"
        with(tableView) {
            column("微信号", User::userName).weigthedWidth(1)
            column("姓名", User::realName).weigthedWidth(1)
            column("手机号", User::mobile)
            column("添加时间", User::createTime).cellFormat {
                text = DateUtil.dateStr4(it)
            }
            anchorpaneConstraints {
                bottomAnchor = 40.0
                leftAnchor = 0.0
                rightAnchor = 0.0
                topAnchor = 0.0
            }
            columnResizePolicy = SmartResize.POLICY
        }
        this += tableView
        this += pagesTool
    }
}