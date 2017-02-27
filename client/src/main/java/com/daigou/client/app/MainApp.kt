package com.daigou.client.app

import com.daigou.client.view.MainView
import javafx.application.Application
import tornadofx.App
import tornadofx.importStylesheet

/**
 * Created by wt on 2017/2/9.
 */
fun main(args: Array<String>) {
    Application.launch(MainApp::class.java)
}
class MainApp : App(MainView::class){
    init {
        importStylesheet("/style.css")
    }
}

