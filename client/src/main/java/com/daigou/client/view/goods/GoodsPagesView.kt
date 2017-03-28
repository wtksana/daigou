package com.daigou.client.view.goods

import com.daigou.client.controller.GoodsCtrl
import com.daigou.client.view.PagesTool
import com.daigou.client.view.user.GoodsView
import com.daigou.common.util.DateUtil
import com.daigou.core.domain.Entity
import com.daigou.core.domain.Goods
import javafx.scene.control.TableView
import javafx.scene.layout.AnchorPane
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*

/**
 * Created by wt on 2017/2/24.
 */
class GoodsPagesView : View() {
    override val root = AnchorPane()
    val goodsCtrl: GoodsCtrl by inject()
    val tableView = TableView<Goods>()
    val pagesTool = PagesTool(goodsCtrl, tableView,Entity.Goods)

    init {
        title = "商品列表"
        with(tableView) {
            column("名称", Goods::goodsName).weigthedWidth(1)
            column("类别", Goods::typeName)
            column("售价", Goods::price)
            column("专柜价", Goods::counter)
            column("进价", Goods::bid)
//            column("地址", User::addressProperty)
//            column("备注", User::remarkProperty)
            column("添加时间", Goods::createTime).cellFormat {
                text = DateUtil.dateStr4(it)
            }
            anchorpaneConstraints {
                bottomAnchor = 40.0
                leftAnchor = 0.0
                rightAnchor = 0.0
                topAnchor = 0.0
            }
            columnResizePolicy = SmartResize.POLICY
        }
        this += tableView
        this += pagesTool
    }
}