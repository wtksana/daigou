package com.daigou.client.app

import com.daigou.client.view.MainView
import tornadofx.App
import tornadofx.Rest

/**
 * Created by wt on 2017/2/9.
 */
class MainApp : App(MainView::class) {
    val api: Rest by inject()

    init {
        api.baseURI = "127.0.0.1:8080/"
    }
}