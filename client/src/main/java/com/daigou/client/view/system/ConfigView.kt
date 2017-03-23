package com.daigou.client.view.system

import com.daigou.common.constant.UrlConstant
import org.controlsfx.control.Notifications
import tornadofx.*

/**
 * Created by wt on 2017/3/19.
 */
class ConfigView : View() {

    val api: Rest by inject()

    override val root = anchorpane {
        prefHeight = 575.0
        prefWidth = 800.0
    }

    val configForm = Form()

    init {
        initConfigForm()
        this += configForm
    }

    fun initConfigForm() {
        with(configForm) {
            fieldset {
                field("连接地址：") {
                    val serverUrl = combobox<String> {
                        isEditable = true
                        items.add(UrlConstant.server_url)
                        items.add("http://1f6013232j.imwork.net")
                    }
                    button("保存") {
                        setOnAction {
                            api.baseURI = serverUrl.value
                            Notifications.create().text("操作成功！").owner(primaryStage).showWarning()
                        }
                    }
                }
            }
        }
    }
}