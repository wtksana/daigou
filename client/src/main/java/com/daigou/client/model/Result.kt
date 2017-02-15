package com.daigou.client.model

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import javax.json.JsonObject

/**
 * Created by wt on 2017/2/15.
 */
class Result : JsonModel {

    val resultProperty = SimpleBooleanProperty()
    var result: Boolean by resultProperty

    val msgProperty = SimpleStringProperty()
    var msg: String by msgProperty

    val dataProperty = SimpleObjectProperty<JsonObject>()
    var data by dataProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            result = bool("result") ?: false
            msg = string("msg") ?: ""
            data = jsonObject("data")
        }
    }

}