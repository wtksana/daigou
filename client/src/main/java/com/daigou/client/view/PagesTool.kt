package com.daigou.client.view

import com.daigou.client.controller.BaseCtrl
import com.daigou.client.model.PagesModel
import com.daigou.client.util.exportExcel
import com.daigou.core.domain.Entity
import com.jfoenix.controls.JFXSpinner
import javafx.geometry.Pos
import javafx.scene.control.TableView
import javafx.scene.input.KeyCode
import javafx.scene.input.MouseButton
import javafx.scene.layout.FlowPane
import javafx.stage.FileChooser
import javafx.stage.Popup
import org.controlsfx.control.Notifications
import tornadofx.*
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * Created by wt on 2017/2/11.
 */
class PagesTool<T>(ctrl: BaseCtrl<T>, table: TableView<T>, entity: Entity) : Fragment() {
    override val root = FlowPane()
    val ctrl = ctrl
    val table = table
    val pagesModel = PagesModel()
    val search = textfield {
        prefWidth = 100.0
        promptText = "搜索"
        bind(pagesModel.search)
        setOnKeyReleased { e ->
            if (e.code == KeyCode.ENTER) {
                getList()
            }
        }
    }


    val ops = button("条件") {
        val opBox = vbox {
            datepicker {
                prefWidth = 100.0
                bind(pagesModel.startTime)
//                value = LocalDate.of(2017, 1, 1)
            }
            datepicker {
                prefWidth = 100.0
                bind(pagesModel.endTime)
//                value = LocalDate.now()
//                setDayCellFactory {
//                    object : DateCell() {
//                        override fun updateItem(item: LocalDate, empty: Boolean) {
//                            super.updateItem(item, empty)
//                            if (item.isBefore(startTime.value.plusDays(1))) {
//                                isDisable = true
//                                style = "-fx-background-color: #ffc0cb;"
//                            }
//                        }
//                    }
//                }
            }
        }

        val pop = Popup()
        pop.isAutoHide = true
        pop.content.add(opBox)
        setOnAction {
            if (pop.isShowing) {
                pop.hide()
            } else {
                val point = this.localToScene(0.0, 0.0)
                pop.x = primaryStage.x + point.x
                pop.y = primaryStage.y + point.y - 25.0
                pop.show(primaryStage)
            }
        }
    }

    val refresh = button("=") {
        setOnMouseClicked { e ->
            if (e.button == MouseButton.PRIMARY) {
                if (page.text.isNullOrBlank()) {
                    page.text = "1"
                }
                getList()
            }
        }
    }

    val previous = button("←") {
        setOnMouseClicked { e ->
            if (e.button == MouseButton.PRIMARY) {
                if (page.text.isNullOrBlank()) {
                    page.text = "1"
                }
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
        bind(pagesModel.pageNum)
        textProperty().addListener { _, _, new ->
            if (!new.matches("[\\d*]".toRegex())) {
                text = new.replace("[^\\d]".toRegex(), "")
            }
        }
        setOnKeyReleased { e ->
            if (e.code == KeyCode.ENTER) {
                if (text.isNullOrBlank()) {
                    text = "1"
                }
                if (text.toInt() <= ctrl.pages.totalPage) {
                    getList()
                }
            }
        }
    }

    val next = button("→") {
        setOnMouseClicked { e ->
            if (e.button == MouseButton.PRIMARY) {
                if (page.text.isNullOrBlank()) {
                    page.text = "1"
                }
                val next = page.text.toInt() + 1
                if (next <= ctrl.pages.totalPage) {
                    page.text = next.toString()
                    getList()
                }
            }
        }
    }

    val totalPage = text {
        text = "共0页"
    }

    val spinner = JFXSpinner()

    val export = button("导出全部") {
        setOnAction {
            val fileChooser = FileChooser()
            fileChooser.initialDirectory = File(System.getProperty("user.home"))
            fileChooser.initialFileName = "${entity.name}_${LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE)}.xlsx"
            fileChooser.extensionFilters.add(FileChooser.ExtensionFilter("excel files (*.xlsx)", "*.xlsx"))
            val file = fileChooser.showSaveDialog(currentWindow)
            file?.let {
                spinner.show()
                runAsync {
                    val list = ctrl.getAll(pagesModel)
                    exportExcel(entity, list, it)
                } ui { rst ->
                    spinner.hide()
                    if (rst == null) {
                        Notifications.create().text("操作成功！").owner(primaryStage).showWarning()
                    } else {
                        Notifications.create().text(rst).owner(primaryStage).showError()
                    }
                }
            }
        }
    }

    init {
        with(root) {
            prefHeight = 40.0
            anchorpaneConstraints {
                bottomAnchor = 0.0
                leftAnchor = 0.0
                rightAnchor = 0.0
            }
            alignment = Pos.CENTER_LEFT
            hgap = 3.0
        }
        this += spinner
        spinner.hide()
//        this += option
//        startTime
//        endTime
//        this += previous
//        this += refresh
//        this += page
//        this += next
//        this += totalPage
        getList()
    }

    fun getList() {
        runAsync {
            ctrl.getList(pagesModel)
        } ui { rst ->
            table.items = rst.observable()
            table.refresh()
            page.text = ctrl.pages.pageNum.toString()
            totalPage.text = "共${ctrl.pages.totalPage}页"
        }
    }

}