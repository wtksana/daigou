package com.daigou.client.view

import com.daigou.client.controller.OperatorCtrl
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Scene
import org.controlsfx.control.Notifications
import tornadofx.*

/**
 * Created by wt on 2017/3/22.
 */
class LoginView : View() {
    val api: Rest by inject()
    val operatorCtrl: OperatorCtrl by inject()
    override val root = form {
        minHeight = 200.0
        minWidth = 300.0
    }
    val serverUrl = SimpleStringProperty()
    val userName = SimpleStringProperty()
    val pwd = SimpleStringProperty()

    init {
        Rest.useApacheHttpClient()
        primaryStage.isResizable = false
        title = "代购"
        with(root) {
            fieldset {
                field("连接地址：") {
                    combobox<String> {
                        serverUrl.bind(valueProperty())
                        valueProperty().value = "http://127.0.0.1:8080"
                        isEditable = true
                        items.add("http://127.0.0.1:8080")
                        items.add("http://1f6013232j.imwork.net")
                    }
                }
                field("用户名") {
                    textfield {
                        userName.bind(textProperty())
                        promptText = "请输入用户名"
                    }
                }
                field("密码") {
                    textfield {
                        pwd.bind(textProperty())
                        promptText = "请输入密码"
                    }
                }
                field("操作：") {
                    button("登录") {
                        setOnAction {
                            if (!serverUrl.value.isNullOrEmpty() && !userName.value.isNullOrEmpty() && !pwd.value.isNullOrEmpty()) {
                                api.baseURI = serverUrl.value
                                runAsync {
                                    operatorCtrl.login(userName.value, pwd.value)
                                } ui { rst ->
                                    if (rst) {
                                        primaryStage.scene = Scene(MainView().root)
                                    } else {
                                        Notifications.create().text("用户名或密码错误！").owner(this).showError()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}