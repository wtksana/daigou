package com.daigou.client.model

import com.daigou.core.domain.Order
import com.daigou.core.domain.OrderDetail
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import java.util.*

/**
 * Created by wt on 2017/2/23.
 */
class OrderModel : ItemViewModel<Order>() {
    val uuid = bind { SimpleStringProperty(item?.uuid) }
    val userUuid = bind { SimpleStringProperty(item?.userUuid) }
    val userName = bind { SimpleStringProperty(item?.userName) }
    val account = bind { SimpleDoubleProperty(item?.account ?: 0.00) }
    val opUuid = bind { SimpleStringProperty(item?.opUuid) }
    val status = bind { SimpleIntegerProperty(item?.status ?: 1) }
    val remark = bind { SimpleStringProperty(item?.remark) }
    val createTime = bind { SimpleObjectProperty<Date>(item?.createTime) }
    val doneTime = bind { SimpleObjectProperty<Date>(item?.doneTime) }
    val updateTime = bind { SimpleObjectProperty<Date>(item?.updateTime) }
    val detail = bind { SimpleStringProperty(item?.detail) }
}