package com.daigou.client.model

import com.daigou.core.domain.Goods
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import java.util.*

/**
 * Created by wt on 2017/2/15.
 */
class GoodsModel : ItemViewModel<Goods>() {
    val uuid = bind { SimpleStringProperty(item?.uuid) }
    val typeUuid = bind { SimpleStringProperty(item?.typeUuid) }
    val type = bind { SimpleStringProperty(item?.type) }
    val name = bind { SimpleStringProperty(item?.name) }
    val price = bind { SimpleDoubleProperty(item?.price ?: 0.00) }
    val counter = bind { SimpleDoubleProperty(item?.counter ?: 0.00) }
    val bid = bind { SimpleDoubleProperty(item?.bid ?: 0.00) }
    val remark = bind { SimpleStringProperty(item?.remark) }
    val createTime = bind { SimpleObjectProperty<Date>(item?.createTime) }
}