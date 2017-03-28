package com.daigou.client.model

import com.daigou.core.domain.GoodsType
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.ItemViewModel
import java.util.*

/**
 * Created by wt on 2017/2/15.
 */
class GoodsTypeModel : ItemViewModel<GoodsType>() {
    val uuid = bind { SimpleStringProperty(item?.uuid) }
    val typeName = bind { SimpleStringProperty(item?.typeName) }
    val status = bind { SimpleIntegerProperty(item?.status ?: 1) }
    val createTime = bind { SimpleObjectProperty<Date>(item?.createTime) }
}