package com.daigou.client.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.time.LocalDate
import javax.json.JsonArray
import javax.json.JsonObject

/**
 * Created by wt on 2017/2/11.
 */
class Pages : JsonModel {
    val pageNumProperty = SimpleIntegerProperty()
    var pageNum by pageNumProperty

    val pageSizeProperty = SimpleIntegerProperty()
    var pageSize by pageSizeProperty

    val searchProperty = SimpleStringProperty()
    var search: String? by searchProperty

    val totalProperty = SimpleLongProperty()
    var total by totalProperty

    val totalPageProperty = SimpleLongProperty()
    var totalPage by totalPageProperty

    val dataProperty = SimpleObjectProperty<JsonArray>()
    var data by dataProperty

    val startTimeProperty = SimpleObjectProperty<LocalDate>()
    var startTime by startTimeProperty

    val endTimeProperty = SimpleObjectProperty<LocalDate>()
    var endTime by endTimeProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            pageNum = int("pageNum")!!
            pageSize = int("pageSize")!!
            total = long("total")!!
            totalPage = long("totalPage")!!
            search = string("search")
            data = jsonArray("data")
            startTime = date("startTime")
            endTime = date("endTime")
        }
    }

    constructor(pageNum: Int, pageSize: Int) {
        this.pageNum = pageNum
        this.pageSize = pageSize
    }

    constructor()

}

class PagesModel : ItemViewModel<Pages>() {
    val pageNum = bind { item?.pageNumProperty }
    val pageSize = bind { item?.pageSizeProperty }
    val search = bind { item?.searchProperty }
    val total = bind { item?.totalProperty }
    val totalPage = bind { item?.totalPageProperty }
    val data = bind { item?.dataProperty }
    val startTime = bind { item?.startTimeProperty }
    val endTime = bind { item?.endTimeProperty }
}