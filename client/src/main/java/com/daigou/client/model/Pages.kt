package com.daigou.client.model

import javafx.beans.property.SimpleIntegerProperty
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

    val totalProperty = SimpleIntegerProperty()
    var total by totalProperty

    val dataProperty = SimpleObjectProperty<JsonArray>()
    var data by dataProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            page = int("page")!!
            row = int("row")!!
            total = int("total")!!
            option = string("option")
            data = jsonArray("data")
        }
    }
}