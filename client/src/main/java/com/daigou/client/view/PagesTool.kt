package com.daigou.client.view

import javafx.geometry.Pos
import javafx.scene.control.ToolBar
import tornadofx.*

/**
 * Created by wt on 2017/2/11.
 */
class PagesTool : Fragment() {
    override val root = ToolBar()

    val option = textfield {
        promptText = "搜索"
    }

    val previous = button("上一页") {

    }

    val page = textfield {
        maxWidth = 30.0
        alignment = Pos.CENTER
        text = "1"
    }

    val next = button("下一页") {

    }

    val totalPage = text {
        text = "共0页"
    }

    init {
        with(root) {
            prefHeight = 40.0
            anchorpaneConstraints {
                bottomAnchor = 0.0
                leftAnchor = 0.0
                rightAnchor = 0.0
            }
        }
        option
        previous
        page
        next
        totalPage
    }


}