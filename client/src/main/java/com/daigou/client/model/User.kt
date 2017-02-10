package com.daigou.client.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.util.*
import javax.json.JsonObject

/**
 * Created by wt on 2017/2/10.
 */
class User : JsonModel {
    val uuidProperty = SimpleStringProperty()
    var uuid by uuidProperty

    val wechatProperty = SimpleStringProperty()
    var wechat by wechatProperty

    val realNameProperty = SimpleStringProperty()
    var realName by realNameProperty

    val mobileProperty = SimpleStringProperty()
    var mobile by mobileProperty

    val addressProperty = SimpleStringProperty()
    var address by addressProperty

    val statusProperty = SimpleIntegerProperty()
    var status by statusProperty

    val remarkProperty = SimpleStringProperty()
    var remark by remarkProperty

    val createTimeProperty = SimpleObjectProperty<Date>()
    var createTime by createTimeProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            uuid = string("uuid")
            wechat = string("wechat")
            realName = string("realName")
            mobile = string("mobile")
            address = string("address")
            status = int("status")!!
            remark = string("remark")
            createTime = Date(getLong("createTime"))
        }
    }
}

class UserModel : ItemViewModel<User>() {
    val uuid = bind { item?.uuidProperty }
    val wechat = bind { item?.wechatProperty }
    val realName = bind { item?.realNameProperty }
    val mobile = bind { item?.mobileProperty }
    val address = bind { item?.addressProperty }
    val status = bind { item?.statusProperty }
    val remark = bind { item?.remarkProperty }
    val createTime = bind { item?.createTimeProperty }
}