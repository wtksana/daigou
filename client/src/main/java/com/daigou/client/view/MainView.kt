package com.daigou.client.view

import com.daigou.client.controller.OperatorCtrl
import javafx.scene.layout.AnchorPane
import tornadofx.View

/**
 * Created by wt on 2017/2/9.
 */
class MainView : View("代购系统") {
    val operatorCtrl: OperatorCtrl by inject()
    override val root: AnchorPane by fxml("/fxml/main.fxml")
}