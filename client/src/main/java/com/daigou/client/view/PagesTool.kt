package com.daigou.client.view

import com.daigou.client.controller.BaseCtrl
import javafx.geometry.Pos
import javafx.scene.control.TableView
import javafx.scene.control.ToolBar
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import tornadofx.*

/**
 * Created by wt on 2017/2/11.
 */
class PagesTool<T>(ctrl: BaseCtrl<T>, table: TableView<T>) : Fragment() {
    override val root = ToolBar()
    val ctrl = ctrl
    val table = table
    val option = textfield {
        promptText = "搜索"
        setOnKeyReleased { e ->
            if (e.code == KeyCode.ENTER) {
                getList()
            }
        }
    }

    val previous = button("上一页") {
        setOnMouseClicked { e ->
            if (e.button == MouseButton.PRIMARY) {
                val previous = page.text.toInt() - 1
                if (previous > 0) {
                    page.text = previous.toString()
                    getList()
                }
            }
        }
    }

    val page = textfield {
        maxWidth = 30.0
        alignment = Pos.CENTER
        text = "1"
        textProperty().addListener { value, old, new ->
            if (!new.matches("[\\d*]".toRegex())) {
                text = new.replace("[^\\d]".toRegex(), "")
            }
        }
        setOnKeyReleased { e ->
            if (e.code == KeyCode.ENTER) {
                getList()
            }
        }
    }

    val next = button("下一页") {
        setOnMouseClicked { e ->
            if (e.button == MouseButton.PRIMARY) {
                val next = page.text.toInt() + 1
                if (next > 0) {
                    page.text = next.toString()
                    getList()
                }
            }
        }
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
        getList()
    }

    fun getList() {
        runAsync {
            ctrl.getList(page.text.toInt(), 20, option.text)
        } ui { rst ->
            if (rst) {
                table.items = ctrl.list
                table.refresh()
                totalPage.text = "共${ctrl.pages.totalPage}页"
            }
        }
    }

}