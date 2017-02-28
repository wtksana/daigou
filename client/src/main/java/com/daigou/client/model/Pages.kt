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
    val pageProperty = SimpleIntegerProperty()
    var page by pageProperty

    val rowProperty = SimpleIntegerProperty()
    var row by rowProperty

    val optionProperty = SimpleStringProperty()
    var option: String? by optionProperty

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
            page = int("page")!!
            row = int("row")!!
            total = long("total")!!
            totalPage = long("totalPage")!!
            option = string("option")
            data = jsonArray("data")
            startTime = date("startTime")
            endTime = date("endTime")
        }
    }

    constructor(page: Int, row: Int) {
        this.page = page
        this.row = row
    }

    constructor()

}

class PagesModel : ItemViewModel<Pages>() {
    val page = bind { item?.pageProperty }
    val row = bind { item?.rowProperty }
    val option = bind { item?.optionProperty }
    val total = bind { item?.totalProperty }
    val totalPage = bind { item?.totalPageProperty }
    val data = bind { item?.dataProperty }
    val startTime = bind { item?.startTimeProperty }
    val endTime = bind { item?.endTimeProperty }
}