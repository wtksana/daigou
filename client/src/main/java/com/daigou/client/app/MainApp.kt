package com.daigou.client.app

import com.daigou.client.view.MainView
import javafx.application.Application
import tornadofx.*

/**
 * 主程序运行这个
 * Created by wt on 2017/2/9.
 */
class MainApp : App(MainView::class) {
    init {
        importStylesheet("/style.css")
    }
}

fun main(args: Array<String>) {
    Application.launch(MainApp::class.java)
}
