package com.daigou.client.model

import com.daigou.core.domain.User
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import java.util.*

/**
 * Created by wt on 2017/2/14.
 */
class UserModel : ItemViewModel<User>() {
    val uuid = bind { SimpleStringProperty(item?.uuid) }
    val userName = bind { SimpleStringProperty(item?.userName) }
    val pwd = bind { SimpleStringProperty(item?.pwd) }
    val realName = bind { SimpleStringProperty(item?.realName) }
    val mobile = bind { SimpleStringProperty(item?.mobile) }
    val address = bind { SimpleStringProperty(item?.address) }
    val remark = bind { SimpleStringProperty(item?.remark) }
    val inviteUser = bind { SimpleStringProperty(item?.inviteUser) }
    val createTime = bind { SimpleObjectProperty<Date>(item?.createTime) }
}