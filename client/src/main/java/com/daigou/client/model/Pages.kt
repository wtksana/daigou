package com.daigou.client.model

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
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

    override fun updateModel(json: JsonObject) {
        with(json) {
            page = int("page")!!
            row = int("row")!!
            total = long("total")!!
            totalPage = long("totalPage")!!
            option = string("option")
            data = jsonArray("data")
        }
    }

}

class PagesModel : ItemViewModel<Pages>() {
    val page = bind { item?.pageProperty }
    val row = bind { item?.rowProperty }
    val option = bind { item?.optionProperty }
    val total = bind { item?.totalProperty }
    val totalPage = bind { item?.totalPageProperty }
    val data = bind { item?.dataProperty }
}