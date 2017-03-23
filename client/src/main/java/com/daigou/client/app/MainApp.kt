package com.daigou.client.app

import com.daigou.client.view.LoginView
import javafx.application.Application
import tornadofx.*

/**
 * 主程序运行这个
 * Created by wt on 2017/2/9.
 */
class MainApp : App(LoginView::class) {
    init {
        importStylesheet("/style.css")
    }
}

fun main(args: Array<String>) {
    Application.launch(MainApp::class.java)
}
