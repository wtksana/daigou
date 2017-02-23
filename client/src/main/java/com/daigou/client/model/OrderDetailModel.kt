package com.daigou.client.model

import com.daigou.core.domain.OrderDetail
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel

/**
 * Created by wt on 2017/2/23.
 */
class OrderDetailModel : ItemViewModel<OrderDetail>() {
    val uuid = bind { SimpleStringProperty(item?.uuid) }
    val orderUuid = bind { SimpleStringProperty(item?.orderUuid) }
    val goodsUuid = bind { SimpleStringProperty(item?.goodsUuid) }
    val quantity = bind { SimpleIntegerProperty(item?.quantity ?: 0) }
    val account = bind { SimpleDoubleProperty(item?.account ?: 0.00) }
    val status = bind { SimpleIntegerProperty(item?.status ?: 1) }
}